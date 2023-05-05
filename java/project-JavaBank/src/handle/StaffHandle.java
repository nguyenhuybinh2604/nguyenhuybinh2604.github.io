package handle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import entity.*;

public class StaffHandle {

    public void overview(SummaryHandle summaryHandle, Map<String, Object> users, List<Product> products, String staffUsername) {
        Staff staff = (Staff) users.get(staffUsername);
        int staffId = staff.getStaffId();
        // get all products assigned to the staff
        List<Product> filteredProducts = products.stream()
                .filter(o -> o.getStaffId() != null)
                .filter(o -> o.getStaffId() == staffId)
                .filter(o -> o.getProductStatus() != ProductStatus.INACTIVE) //chi lay active & locked
                .collect(Collectors.toList());
        // Pivoted Sum
        Map<Integer, Summary> result = summaryHandle.byCustomer(filteredProducts);
        // calculate total array
        Summary total = summaryHandle.getTotal(result);
        System.out.println("Summary for staff No." + staffId + " - " + staff.getName() + ":");
        System.out.printf("%-50s%,33d\n", "Number of customers:", result.size());
        // display total array data
        summaryHandle.displaySummary(total);
    }

    public void viewListOfCustomers(InputControl inputControl, SummaryHandle summaryHandle, CustomerHandle customerHandle,
                                    Map<String, Object> users, List<Product> products, String staffUsername) {
        Staff staff = (Staff) users.get(staffUsername);
        int staffId = staff.getStaffId();
        // get all products assigned to the staff
        List<Product> filteredProducts = products.stream()
                .filter(o -> o.getStaffId() != null)
                .filter(o -> o.getStaffId() == staffId)
                .filter(o -> o.getProductStatus() != ProductStatus.INACTIVE) //chi lay active & locked
                .collect(Collectors.toList());
        // Pivoted Sum
        Map<Integer, Summary> result = summaryHandle.byCustomer(filteredProducts);
        summaryHandle.displayDetail(inputControl, customerHandle, users, result);
    }

    //only if customer already has credit rating, if not return to update Rating
    public void approveLoans(Scanner sc, InputControl inputControl, CustomerHandle customerHandle,
                             ProductHandle productHandle, Map<String, Object> users, List<Product> products,
                             List<InterestRate> interestRates, List<ExchangeRate> exchangeRates, String staffUsername) {
        Staff staff = (Staff) users.get(staffUsername);
        int staffId = staff.getStaffId();
        // get all locked loans assigned to the staff
        Map<Integer, Product> productMap = products.stream()
                .filter(o -> o.getStaffId() != null)
                .filter(o -> o.getStaffId() == staffId)
                .filter(o -> o.getProductType() == ProductType.LOAN)
                .filter(o -> o.getProductStatus() == ProductStatus.LOCKED)
                .collect(Collectors.toMap(Product::getProductId, o -> o));
        if (productMap.size() > 0) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            //list all related loans with LOCKED status, choose one
            for (Product product : productMap.values()) {
                System.out.println(product.toString(inputControl, formatter));
            }
            System.out.println("Select a loan Id:");
            int loanId = inputControl.getInput(sc, 1, null);
            if (productMap.containsKey(loanId)) {
                Product product = productMap.get(loanId);
                //check whether if bank has enough money
                int customerId = product.getCustomerId();
                String customerUsername = customerHandle.findCustomer(users, customerId);
                if (customerUsername != null) {
                    Customer customer = (Customer) users.get(customerUsername);
                    CreditRating creditRating = customer.getCreditRating();
                    if (creditRating != CreditRating.UNKNOWN) {
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
                                        product.getCurrency(), product.getTenor(), inputControl.toCreditRatingStr(creditRating));
                                product.setInterestRate(interestRate);
                                double exchangeRate = productHandle.getExchangeRate(exchangeRates, product.getCurrency(), "VND");
                                product.setConvertedBalance(product.getBalance() * exchangeRate);
                                product.setProductStatus(ProductStatus.ACTIVE);
                                // log a message to customer
                            }
                            // declines -> set proposed loan as INACTIVE
                            case 2 -> {
                                // log a message to customer
                                product.setProductStatus(ProductStatus.INACTIVE);
                            }
                        }
                    } else System.out.println("Customer has not been rated. Update credit rating");
                } else System.out.println("Customer not found");
            } else System.out.println("Loan Id not found");
        } else System.out.println("No loan on waiting list");
    }

    public void updateRating(Scanner sc, InputControl inputControl, CustomerHandle customerHandle, Map<String, Object> users) {
        System.out.println("Select customer Id:");
        int customerId = inputControl.getInput(sc, 1, null);
        // check if customerId exists
        if (customerHandle.findCustomer(users, customerId) != null) {
            Customer customer = (Customer) users.get(customerHandle.findCustomer(users, customerId));
            System.out.println("Enter new credit rating (A, B, C):");
            String newRating = inputControl.getNonEmptyString(sc);
            if (newRating.equalsIgnoreCase("A") || newRating.equalsIgnoreCase("B")
                    || newRating.equalsIgnoreCase("C")) {
                //create request to manager to update rating
//                customer.setCreditRating(inputControl.toCreditRating(newRating.toUpperCase(Locale.ROOT)));
                System.out.println("Rating submitted for approval");
            } else System.out.println("Must choose A, B or C");
        } else System.out.println("Customer Id not found");
    }

}
