package handle;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import entity.*;

public class StaffHandle {

    public void overview(CustomerHandle customerHandle, SummaryHandle summaryHandle, Map<String, Object> users, List<Product> products, String staffUsername) {
        if (users != null && users.size() > 0 && users.containsKey(staffUsername)) {
            Staff staff = (Staff) users.get(staffUsername);
            int staffId = staff.getStaffId();
            if (products != null && products.size() > 0) {

                // get all products assigned to the staff
                List<Product> filteredProducts = products.stream()
                        .filter(o -> o.getStaffId() != null)
                        .filter(o -> o.getStaffId() == staffId)
                        .filter(o -> o.getProductStatus() != ProductStatus.INACTIVE) //chi lay active & locked
                        .collect(Collectors.toList());
                if (filteredProducts.size() > 0) {

                    // Pivoted Sum
                    Map<Integer, Summary> result = summaryHandle.byCustomer(customerHandle, users, filteredProducts);

                    // calculate total array
                    Summary total = summaryHandle.getTotal(result);
                    System.out.println("Summary for staff No." + staffId + " - " + staff.getName() + ":");
                    System.out.printf("%-50s%,33d\n", "Number of customers:", result.size());

                    // display total array data
                    summaryHandle.displaySummary(total);
                } else System.out.println("No record");
            } else System.out.println("No record");
        } else System.out.println("Staff not found");
    }

    public void viewListOfCustomers(SummaryHandle summaryHandle, CustomerHandle customerHandle,
                                    Map<String, Object> users, List<Product> products, String staffUsername) {
        if (users != null && users.size() > 0 && users.containsKey(staffUsername)) {
            Staff staff = (Staff) users.get(staffUsername);
            int staffId = staff.getStaffId();
            if (products != null && products.size() > 0) {

                // get all products assigned to the staff
                List<Product> filteredProducts = products.stream()
                        .filter(o -> o.getStaffId() != null)
                        .filter(o -> o.getStaffId() == staffId)
                        .filter(o -> o.getProductStatus() != ProductStatus.INACTIVE) //chi lay active & locked
                        .collect(Collectors.toList());
                if (filteredProducts.size() > 0) {

                    // Pivoted Sum
                    Map<Integer, Summary> result = summaryHandle.byCustomer(customerHandle, users, filteredProducts);
                    summaryHandle.displayDetailCustomer(customerHandle, users, result);
                } else System.out.println("No record");
            } else System.out.println("No record");
        } else System.out.println("Staff not found");
    }

    //only if customer already has credit rating, if not return to update Rating
    public void approveLoans(Scanner sc, InputControl inputControl, CustomerHandle customerHandle,
                             ProductHandle productHandle, BalanceSheet balanceSheet, Map<String, Object> users,
                             List<Product> products, List<InterestRate> interestRates, List<ExchangeRate> exchangeRates,
                             String staffUsername) {
        if (users != null && users.size() > 0 && users.containsKey(staffUsername)) {
            Staff staff = (Staff) users.get(staffUsername);
            int staffId = staff.getStaffId();
            if (products != null && products.size() > 0) {

                // get all locked loans assigned to the staff
                Map<Integer, Product> productMap = products.stream()
                        .filter(o -> o.getStaffId() != null)
                        .filter(o -> o.getStaffId() == staffId)
                        .filter(o -> o.getProductType() == ProductType.LOAN)
                        .filter(o -> o.getProductStatus() == ProductStatus.LOCKED)
                        .collect(Collectors.toMap(Product::getProductId, o -> o));
                if (productMap.size() > 0) {

                    //list all related loans with LOCKED status, choose one
                    System.out.printf("%-10s%-10s%12s%12s%10s%10s%30s%30s%8s%10s%10s\n", "IDs", "Staff IDs",
                            "Value Date", "Maturity", "Tenor (M)", "Currency", "Balance",
                            "Balance in VND", "IR", "Status", "Type");
                    for (Product product : productMap.values())
                        System.out.println(product.toString());
                    System.out.println("Select a loan Id:");
                    int loanId = inputControl.getInput(sc, 1, null);
                    // check if selected loan belong to related loans
                    if (productMap.containsKey(loanId)) {
                        Product product = productMap.get(loanId);

                        double exchangeRate = productHandle.getExchangeRate(exchangeRates, product.getCurrency(), "VND");
                        double remainingMoney = balanceSheet.getCashBalance();
                        double convertedBalance = exchangeRate * product.getBalance();

                        //check whether if bank has enough money
                        //if enough -> continue next steps, if not -> print not enough money
                        if (remainingMoney >= convertedBalance) {

                            // wont change (still pending for approval)
                            int customerId = product.getCustomerId();
                            String customerUsername = customerHandle.findCustomer(users, customerId);
                            if (customerUsername != null) {
                                Customer customer = (Customer) users.get(customerUsername);
                                CreditRating creditRating = customer.getCreditRating();
                                if (creditRating != CreditRating.NA) {
                                    System.out.println("SELECT AN OPTION");
                                    System.out.println("1. Approve loan");
                                    System.out.println("2. Decline loan");
                                    int input = inputControl.getInput(sc, 1, 2);
                                    switch (input) {

                                        // approves -> turn ACTIVE, update all loan properties
                                        case 1 -> {
                                            LocalDate valueDate = LocalDate.now();
                                            product.setValueDate(valueDate);
                                            LocalDate maturityDate = valueDate.plusMonths(product.getTenor());
                                            product.setMaturityDate(maturityDate);
                                            double interestRate = productHandle.getInterestRate(interestRates, ProductType.LOAN,
                                                    product.getCurrency(), product.getTenor(), creditRating.toString());
                                            product.setInterestRate(interestRate);

                                            product.setConvertedBalance(convertedBalance);
                                            product.setProductStatus(ProductStatus.ACTIVE);

                                            // create disbursement account
                                            int disbursementAccountId = productHandle.getNextId(products, ProductType.ACCOUNT);
                                            double disbursementAccountRate = productHandle.getInterestRate(interestRates, ProductType.ACCOUNT, product.getCurrency(), null, null);
                                            Account disbursementAccount = new Account(disbursementAccountId, staffId, valueDate,
                                                    null, null, product.getCurrency(), product.getBalance(), convertedBalance,
                                                    disbursementAccountRate, ProductStatus.ACTIVE);

                                            // add disb account to product lists
                                            products.add(disbursementAccount);
                                            customer.getProducts().add(disbursementAccount);

                                            // change in balance sheet
                                            balanceSheet.setLoanBalance(balanceSheet.getLoanBalance()
                                                    + convertedBalance);
                                            balanceSheet.setDepositBalance(balanceSheet.getDepositBalance()
                                                    + convertedBalance);

                                            System.out.println("Loan proposal No. " + loanId + " from customer " + customer.getName() + " has been approved");
                                        }

                                        // declines -> set proposed loan as INACTIVE
                                        case 2 -> {
                                            // log a message to customer
                                            product.setProductStatus(ProductStatus.INACTIVE);
                                            System.out.println("Loan proposal No. " + loanId + " from customer " + customer.getName() + " has been declined");
                                        }
                                    }
                                } else
                                    System.out.println("Customer No." + customerId + " has not been rated. Update credit rating");
                            } else System.out.println("Customer not found");
                        } else System.out.println("Not sufficient fund");
                    } else System.out.println("Loan Id not found");
                } else System.out.println("No loan on waiting list");
            } else System.out.println("No record");
        } else System.out.println("Staff not found");
    }

    // create an active rating update request
    // applies for all customers
    public void updateRating(Scanner sc, InputControl inputControl, CustomerHandle customerHandle,
                             RatingHandle ratingHandle, Map<String, Object> users, List<RatingUpdateRequest> ratingUpdateRequests,
                             String staffUsername
    ) {
        System.out.println("Select customer Id:");
        int customerId = inputControl.getInput(sc, 1, null);
        if (customerHandle.findCustomer(users, customerId) != null) {
            String customerUsername = customerHandle.findCustomer(users, customerId);
            Customer customer = (Customer) users.get(customerUsername);

            // check if customer relates to staff
            int staffId = ((Staff) users.get(staffUsername)).getStaffId(); //cant be null
            if (findStaff(customer, staffId) != null) {
                System.out.println("Enter new credit rating (A, B, C):");
                String newRating = inputControl.getNonEmptyString(sc);
                if (newRating.equalsIgnoreCase("A") || newRating.equalsIgnoreCase("B")
                        || newRating.equalsIgnoreCase("C")) {

                    //create message to manager to update rating => turn rating update flag ON
                    int requestId = ratingHandle.getNextId(ratingUpdateRequests);

                    // convert new rating Str to rating type
                    CreditRating proposedRating;
                    if (newRating.equalsIgnoreCase("A")) proposedRating = CreditRating.A;
                    else if (newRating.equalsIgnoreCase("B")) proposedRating = CreditRating.B;
                    else proposedRating = CreditRating.C;

                    RatingUpdateRequest request = new RatingUpdateRequest(requestId, LocalDateTime.now(), staffId, customerId,
                            customer.getCreditRating(), proposedRating, true);

                    ratingUpdateRequests.add(request);

                    System.out.println("Rating submitted for approval");
                } else System.out.println("Must choose A, B or C");
            } else System.out.println("Customer not found");
        } else System.out.println("Customer not found");
    }

    // if any of customer's products has staffId
    private Integer findStaff(Customer customer, int staffId) {
        if (customer.getProducts() != null && customer.getProducts().size() > 0) {
            List<Product> subProducts = customer.getProducts();
            for (Product product : subProducts) {
                if (product.getStaffId() != null && product.getStaffId() == staffId)
                    return staffId;
            }
        } else {
            System.out.println("Customer has no product");
        }
        return null;
    }

    public String findStaff(Map<String, Object> users, int staffId) {
        if (users != null && users.size() > 0) {
            for (Object user : users.values()) {
                if (user.getClass().getSimpleName().equals("Staff")
                        && ((Staff) user).getStaffId() == staffId) {
                    return ((Staff) user).getUsername();
                }
            }
        }
        return null;
    }
}
