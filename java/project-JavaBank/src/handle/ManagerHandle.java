package handle;

import entity.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class ManagerHandle {
    public void overview(CustomerHandle customerHandle, SummaryHandle summaryHandle, Map<String, Object> users, List<Product> products) {
        if (products != null && products.size() > 0) {
            // get all active & locked products
            List<Product> filteredProducts = products.stream()
                    .filter(o -> o.getProductStatus() != ProductStatus.INACTIVE)
                    .collect(Collectors.toList());

            if (filteredProducts.size() > 0) {

                // Pivoted Sum
                Map<Integer, Summary> result = summaryHandle.byCustomer(customerHandle, users, filteredProducts);

                // calculate total array
                Summary total = summaryHandle.getTotal(result);
                System.out.println("Summary for JavaBank:");
                System.out.printf("%-50s%,33d\n", "Number of customers:", result.size());

                // display total array data
                summaryHandle.displaySummary(total);
            } else System.out.println("No record");
        } else System.out.println("No record");
    }

    public void viewListOfCustomers(InputControl inputControl, SummaryHandle summaryHandle, CustomerHandle customerHandle,
                                    Map<String, Object> users, List<Product> products) {
        if (products != null && products.size() > 0) {

            // get all active & locked products
            List<Product> filteredProducts = products.stream()
                    .filter(o -> o.getProductStatus() != ProductStatus.INACTIVE)
                    .collect(Collectors.toList());

            if (filteredProducts.size() > 0) {

                // Pivoted Sum
                Map<Integer, Summary> result = summaryHandle.byCustomer(customerHandle, users, filteredProducts);
                summaryHandle.displayDetailCustomer(customerHandle, users, result);
            } else System.out.println("No record");
        } else System.out.println("No record");
    }

    public void viewListOfStaffs(SummaryHandle summaryHandle, StaffHandle staffHandle,
                                 Map<String, Object> users, List<Product> products) {
        if (products != null && products.size() > 0) {

            // get all products assigned to the staff
            List<Product> filteredProducts = products.stream()
                    .filter(o -> o.getStaffId() != null)
                    .filter(o -> o.getProductStatus() != ProductStatus.INACTIVE) //chi lay active & locked
                    .collect(Collectors.toList());
            if (filteredProducts.size() > 0) {

                // Pivoted Sum
                Map<Integer, Summary> result = summaryHandle.byStaff(users, filteredProducts);

                summaryHandle.displayDetailStaff(staffHandle, users, result);
            } else System.out.println("No record");
        } else System.out.println("No record");

    }

    public void assignStaffs(Scanner sc, InputControl inputControl, SummaryHandle summaryHandle,
                             StaffHandle staffHandle, Map<String, Object> users, List<Product> products) {

        // get list of non-inactive products with missing IDs
        List<Product> productsNullStaff = products.stream()
                .filter(o -> o.getStaffId() == null || o.getStaffId() == 0)
                .filter(o -> o.getProductStatus() != ProductStatus.INACTIVE)
                .collect(Collectors.toList());

        if (productsNullStaff.size() > 0) {
            //show products with missing staff Id
            System.out.printf("%-10s%-10s%12s%12s%10s%10s%30s%30s%8s%10s%10s\n", "IDs", "Staff IDs",
                    "Value Date", "Maturity", "Tenor (M)", "Currency", "Balance",
                    "Balance in VND", "IR", "Status", "Type");
            for (Product product : productsNullStaff)
                System.out.println(product.toString());

            //pick a product: enter product type -> Id
            System.out.println("Enter product type:");
            String productTypeStr = inputControl.getNonEmptyString(sc);

            ProductType productType;

            if (productTypeStr.equalsIgnoreCase("LOAN")) productType = ProductType.LOAN;
            else if (productTypeStr.equalsIgnoreCase("SAVING")) productType = ProductType.SAVING;
            else if (productTypeStr.equalsIgnoreCase("ACCOUNT")) productType = ProductType.ACCOUNT;
            else productType = null;

            if (productType != null) {

                System.out.println("Choose product Id:");
                int productId = inputControl.getInput(sc, 1, null);
                productsNullStaff = productsNullStaff.stream()
                        .filter(o -> o.getProductType() == productType)
                        .filter(o -> o.getProductId() == productId)
                        .collect(Collectors.toList());

                // check if entered values match existing
                // should return only 1 record at this point if matched
                if (productsNullStaff.size() > 0) {

                    Product product = productsNullStaff.get(0);

                    //display list of staffs
                    viewListOfStaffs(summaryHandle, staffHandle, users, products);

                    // get all products assigned to the staff
                    List<Product> productsWithStaff = products.stream()
                            .filter(o -> o.getStaffId() != null)
                            .filter(o -> o.getProductStatus() != ProductStatus.INACTIVE) //chi lay active & locked
                            .collect(Collectors.toList());

                    // Pivoted Sum
                    Map<Integer, Summary> result = summaryHandle.byStaff(users, productsWithStaff);

                    //choose staff Id
                    System.out.println("Select staff ID:");
                    int staffId = inputControl.getInput(sc, 1, null);

                    //check if entered staff Id matched
                    if (result.containsKey(staffId)) {
                        //update product: assign staff Id
                        product.setStaffId(staffId);

                        System.out.println("Staff No." + staffId + " has been assigned to " + productTypeStr + " No." + productId);

                    } else System.out.println("Could not find chosen staff");
                } else System.out.println("Could not find chosen product");
            } else System.out.println("Wrong product type entered");
        } else System.out.println("No record");
    }

    public void editSalary(Scanner sc, InputControl inputControl, SummaryHandle summaryHandle,
                           StaffHandle staffHandle, Map<String, Object> users, List<Product> products) {

        //display list of staffs
        viewListOfStaffs(summaryHandle, staffHandle, users, products);

        // get all products assigned to the staff
        List<Product> productsWithStaff = products.stream()
                .filter(o -> o.getStaffId() != null)
                .filter(o -> o.getProductStatus() == ProductStatus.ACTIVE) //chi lay active
                .collect(Collectors.toList());

        // Pivoted Sum
        Map<Integer, Summary> result = summaryHandle.byStaff(users, productsWithStaff);

        //choose staff Id
        System.out.println("Select staff ID:");
        int staffId = inputControl.getInput(sc, 1, null);

        //check if entered staff Id matched
        if (result.containsKey(staffId)) {

            // get staff record
            Staff staff = (Staff) users.get(staffHandle.findStaff(users, staffId));

            System.out.printf("%s%,.2f\n", "Current salary:\n", staff.getBasicSalary());

            System.out.println("Enter new salary:");
            double newSalary = inputControl.getInput(sc, 0.0, null);

            // set new salary
            staff.setBasicSalary(newSalary);

            System.out.println("New salary for staff No." + staffId + " - " + staff.getName() + " has been updated");

        } else System.out.println("Could not find chosen staff");
    }

    public void viewBonus(SummaryHandle summaryHandle, StaffHandle staffHandle, CustomerHandle customerHandle,
                          Map<String, Object> users, List<Product> products) {

        // get all active
        List<Product> activeProducts = products.stream()
                .filter(o -> o.getStaffId() != null)
                .filter(o -> o.getProductStatus() == ProductStatus.ACTIVE)
                .collect(Collectors.toList());

        if (activeProducts.size() > 0) {

            // Pivoted Sum
            Map<Integer, Summary> result = summaryHandle.byCustomer(customerHandle, users, activeProducts);

            // calculate total array
            Summary total = summaryHandle.getTotal(result);

            // get revenue = loan balance x avg Rate - deposit balance x avg Rate
            double totalRevenue = Math.max(0.0, total.getSum(1) - total.getSum(3));

            // get salary fund of all staffs + manager
            double annualSalaryFund = 0.0;

            // reserve manager username
            String managerUsername = "";

            for (Map.Entry<String, Object> entry : users.entrySet()) {
                if (entry.getValue().getClass().getSimpleName().equals("Staff"))
                    annualSalaryFund += ((Staff) entry.getValue()).getBasicSalary();
                else if (entry.getValue().getClass().getSimpleName().equals("Manager")) {
                    annualSalaryFund += ((Manager) entry.getValue()).getBasicSalary();
                    managerUsername = ((Manager) entry.getValue()).getUsername();
                }
            }
            annualSalaryFund *= 12.0;

            Manager manager = (Manager) users.get(managerUsername);

            // calculate bonus components
            double totalProfit = Math.max(0.0, totalRevenue - annualSalaryFund);
            double totalBonus = 0.1 * totalProfit;
            double managerBonus = 0.2 * totalBonus;
            double totalStaffBonus = totalBonus - managerBonus;

            manager.setBonus(managerBonus);

            // get only staffs with at least 1 active products
            Map<Integer, Summary> unsortedStaffs = summaryHandle.byStaff(users, activeProducts);

            List<Map.Entry<Integer, Summary>> list = new ArrayList<>(unsortedStaffs.entrySet());
            Collections.sort(list, Comparator.comparing(Map.Entry::getValue));

            Map<Integer, Summary> sortedStaffs = new LinkedHashMap<>();
            for (Map.Entry<Integer, Summary> entry : list) {
                sortedStaffs.put(entry.getKey(), entry.getValue());
            }

            int noOfStaffs = sortedStaffs.size();
            int firstThird = Math.max(noOfStaffs / 3, 1);
            int secondThird = firstThird * 2;

            int i = 0;

            System.out.println("Estimated bonuses for year " + LocalDate.now().getYear() + " :");
            System.out.printf("\n%5s%10s %-30s%5s%30s%30s\n", "IDs", "Role ", "Name", "Rank", "Salary", "Bonus");

            // Assign rank to each sorted staff + display
            for (Map.Entry<Integer, Summary> entry : sortedStaffs.entrySet()) {
                i++;
                int staffId = entry.getKey();
                String staffUsername = staffHandle.findStaff(users, staffId);
                Staff staff = (Staff) users.get(staffUsername);
                if (i <= firstThird) {
                    staff.setRank(1);
                    staff.setBonus(totalStaffBonus * 0.1);
                } else if (i <= secondThird) {
                    staff.setRank(2);
                    staff.setBonus(totalStaffBonus * 0.05);
                } else if (i <= noOfStaffs) {
                    staff.setRank(3);
                    staff.setBonus(totalStaffBonus * 0.02);
                }

                // print each staff
                System.out.printf("%5d%10s %-30s%5d%,30.2f%,30.2f\n", staffId, "STAFF", staff.getName(), staff.getRank(),
                        staff.getBasicSalary(), staff.getBonus());
            }

            //subtotal line
            System.out.printf("%5s%10s %-30s%5s%30s%,30.2f\n", "ALL", "STAFF", "Subtotal", "", "", totalStaffBonus);

            //manager header
            System.out.printf("\n%5s%10s %-30s%5s%30s%30s\n", "IDs", "Role", "Name", "Rank", "Salary", "Bonus");
            System.out.printf("%5s%10s %-30s%5s%,30.2f%,30.2f\n", "", "MANAGER", manager.getName(), "",
                    manager.getBasicSalary(), manager.getBonus());

        } else System.out.println("No active product");
    }

    public void viewExchangeRate(Scanner sc, InputControl inputControl, List<ExchangeRate> exchangeRates) {
        DateTimeFormatter fmtDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Enter effect date:");
        LocalDate effectDate = inputControl.getLocalDate(sc);

        //find in list
        List<ExchangeRate> filteredRecords = exchangeRates.stream()
                .filter(o -> o.getEffectDate().isEqual(effectDate))
                .collect(Collectors.toList());

        if (filteredRecords.size() > 0) {
            System.out.printf("%20s%20s%20s%20s\n", "Effect Date", "From Currency", "To Currency", "Exchange Rate");
            for (ExchangeRate exchangeRate : filteredRecords) {
                System.out.printf("%20s%20s%20s%,20.2f\n", exchangeRate.getEffectDate().format(fmtDate), exchangeRate.getFromCurrency(),
                        exchangeRate.getToCurrency(), exchangeRate.getExchangeRate());
            }
        } else System.out.println("No record found");
    }

    public void updateExchangeRate(Scanner sc, InputControl inputControl, List<ExchangeRate> exchangeRates) {
//enter effect date
        DateTimeFormatter fmtDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Enter effect date:");
        LocalDate effectDate = inputControl.getLocalDate(sc);

        int stopCondition = 1;
        System.out.println("Effect date: " + effectDate.format(fmtDate));
        do {
            System.out.println("Enter from currency:");
            String fromCurrency = inputControl.getNonEmptyString(sc);

            // search if record already exists in list
            boolean isFound = false;
            ExchangeRate exchangeRateRecord = new ExchangeRate();
            for (ExchangeRate record : exchangeRates) {
                if (record.getEffectDate().equals(effectDate) && record.getFromCurrency().equals(fromCurrency)) {
                    isFound = true;
                    exchangeRateRecord = record;
                    break;
                }
            }

            // if found : ask for overwrite
            if (isFound) {
                System.out.println("Record found. Overwrite?");
                System.out.println("1 - Yes");
                System.out.println("2 - No");
                int inputOverwrite = inputControl.getInput(sc, 1, 2);
                if (inputOverwrite == 1) {
                    System.out.println("Enter exchange rate for " + fromCurrency + ":");
                    double exchangeRate = inputControl.getInput(sc, 0.0, null);
                    exchangeRateRecord.setExchangeRate(exchangeRate);
                }
                //if not found: create new record and add
            } else {
                System.out.println("Enter exchange rate for " + fromCurrency + ":");
                double exchangeRate = inputControl.getInput(sc, 0.0, null);
                exchangeRateRecord.setEffectDate(effectDate);
                exchangeRateRecord.setFromCurrency(fromCurrency);
                exchangeRateRecord.setToCurrency("VND");
                exchangeRateRecord.setExchangeRate(exchangeRate);
                exchangeRates.add(exchangeRateRecord);
            }

            System.out.println("Continue?");
            System.out.println("1 - Yes");
            System.out.println("2 - No");
            stopCondition = inputControl.getInput(sc, 1, 2);
        } while (stopCondition != 2);

    }

    public void viewInterestRate(Scanner sc, InputControl inputControl, List<InterestRate> interestRates) {
        DateTimeFormatter fmtDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Enter effect date:");
        LocalDate effectDate = inputControl.getLocalDate(sc);

        System.out.println("Enter product type (LOAN/SAVING/ACCOUNT):");
        String productTypeStr = inputControl.getNonEmptyString(sc);

        if (productTypeStr.equals("LOAN") || productTypeStr.equals("SAVING") || productTypeStr.equals("ACCOUNT")) {

            //find in list
            List<InterestRate> filteredRecords = interestRates.stream()
                    .filter(o -> o.getEffectDate().isEqual(effectDate))
                    .filter(o -> o.getProductType().toString().equals(productTypeStr))
                    .collect(Collectors.toList());

            if (filteredRecords.size() > 0) {
                System.out.printf("%20s%20s%20s%20s%20s%20s\n", "Effect Date", "Product Type", "Currency", "Tenor", "Credit Rating", "Interest Rate");
                for (InterestRate record : filteredRecords) {
                    System.out.printf("%20s%20s%20s%20d%20s%,20.2f\n", record.getEffectDate().format(fmtDate),
                            record.getProductType().toString(), record.getCurrency(), record.getTenor(),
                            record.getCreditRatingStr(), record.getInterestRate());
                }
            } else System.out.println("No record found");
        } else System.out.println("Wrong product type entered");
    }

    public void updateInterestRate(Scanner sc, InputControl inputControl, ProductHandle
            productHandle, List<InterestRate> interestRates) {

        //enter effect date
        DateTimeFormatter fmtDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Enter effect date:");
        LocalDate effectDate = inputControl.getLocalDate(sc);

        int stopCondition = 1;
        System.out.println("Effect date: " + effectDate.format(fmtDate));
        do {

            System.out.println("Enter product type (LOAN/SAVING/ACCOUNT):");
            String productTypeStr = inputControl.getNonEmptyString(sc);

            if (productTypeStr.equalsIgnoreCase("LOAN")) {

                //display existing currencies
                productHandle.displayCurrencies(interestRates, ProductType.LOAN, effectDate);

                // get currency input
                System.out.println("Enter currency:");
                String currency = inputControl.getNonEmptyString(sc);

                // tenor
                System.out.println("Enter tenor (in months):");
                int tenor = inputControl.getInput(sc, 1, null);

                // credit rating Str
                System.out.println("Enter credit rating:");
                System.out.println("1 - A");
                System.out.println("2 - B");
                System.out.println("3 - C");

                int creditRatingInput = inputControl.getInput(sc, 1, 3);
                String creditRatingStr = "";
                if (creditRatingInput == 1)
                    creditRatingStr = "A";
                else if (creditRatingInput == 2)
                    creditRatingStr = "B";
                else if (creditRatingInput == 3)
                    creditRatingStr = "C";

                boolean isFound = false;
                InterestRate interestRateRecord = new InterestRate();
                for (InterestRate record : interestRates) {
                    if (record.getEffectDate().equals(effectDate)
                            && record.getProductType().equals(ProductType.LOAN)
                            && record.getCurrency().equals(currency)
                            && record.getTenor() == tenor
                            && record.getCreditRatingStr().equals(creditRatingStr)) {
                        isFound = true;
                        interestRateRecord = record;
                        break;
                    }
                }

                // if found : ask for overwrite
                if (isFound) {
                    System.out.println("Record found. Overwrite?");
                    System.out.println("1 - Yes");
                    System.out.println("2 - No");
                    int inputOverwrite = inputControl.getInput(sc, 1, 2);
                    if (inputOverwrite == 1) {
                        System.out.println("Enter interest rate:");
                        double interestRate = inputControl.getInput(sc, 0.0, null);
                        interestRateRecord.setInterestRate(interestRate);
                    }
                    //if not found: create new record and add
                } else {
                    System.out.println("Enter interest rate:");
                    double interestRate = inputControl.getInput(sc, 0.0, null);
                    interestRateRecord.setEffectDate(effectDate);
                    interestRateRecord.setProductType(productTypeStr);
                    interestRateRecord.setCurrency(currency);
                    interestRateRecord.setTenor(tenor);
                    interestRateRecord.setCreditRatingStr(creditRatingStr);
                    interestRateRecord.setInterestRate(interestRate);
                    interestRates.add(interestRateRecord);
                }
            } else if (productTypeStr.equalsIgnoreCase("SAVING")) {

                //display existing currencies
                productHandle.displayCurrencies(interestRates, ProductType.SAVING, effectDate);

                // get currency input
                System.out.println("Enter currency:");
                String currency = inputControl.getNonEmptyString(sc);

                // tenor
                System.out.println("Enter tenor (in months):");
                int tenor = inputControl.getInput(sc, 1, null);

                boolean isFound = false;
                InterestRate interestRateRecord = new InterestRate();
                for (InterestRate record : interestRates) {
                    if (record.getEffectDate().equals(effectDate)
                            && record.getProductType().equals(ProductType.SAVING)
                            && record.getCurrency().equals(currency)
                            && record.getTenor() == tenor) {
                        isFound = true;
                        interestRateRecord = record;
                        break;
                    }
                }

                // if found : ask for overwrite
                if (isFound) {
                    System.out.println("Record found. Overwrite?");
                    System.out.println("1 - Yes");
                    System.out.println("2 - No");
                    int inputOverwrite = inputControl.getInput(sc, 1, 2);
                    if (inputOverwrite == 1) {
                        System.out.println("Enter interest rate:");
                        double interestRate = inputControl.getInput(sc, 0.0, null);
                        interestRateRecord.setInterestRate(interestRate);
                    }
                    //if not found: create new record and add
                } else {
                    System.out.println("Enter interest rate:");
                    double interestRate = inputControl.getInput(sc, 0.0, null);
                    interestRateRecord.setEffectDate(effectDate);
                    interestRateRecord.setProductType(productTypeStr);
                    interestRateRecord.setCurrency(currency);
                    interestRateRecord.setTenor(tenor);
                    interestRateRecord.setInterestRate(interestRate);
                    interestRates.add(interestRateRecord);
                }
            } else if (productTypeStr.equalsIgnoreCase("ACCOUNT")) {

                //display existing currencies
                productHandle.displayCurrencies(interestRates, ProductType.ACCOUNT, effectDate);

                // get currency input
                System.out.println("Enter currency:");
                String currency = inputControl.getNonEmptyString(sc);

                boolean isFound = false;
                InterestRate interestRateRecord = new InterestRate();
                for (InterestRate record : interestRates) {
                    if (record.getEffectDate().equals(effectDate)
                            && record.getProductType().equals(ProductType.ACCOUNT)
                            && record.getCurrency().equals(currency)) {
                        isFound = true;
                        interestRateRecord = record;
                        break;
                    }
                }

                // if found : ask for overwrite
                if (isFound) {
                    System.out.println("Record found. Overwrite?");
                    System.out.println("1 - Yes");
                    System.out.println("2 - No");
                    int inputOverwrite = inputControl.getInput(sc, 1, 2);
                    if (inputOverwrite == 1) {
                        System.out.println("Enter interest rate:");
                        double interestRate = inputControl.getInput(sc, 0.0, null);
                        interestRateRecord.setInterestRate(interestRate);
                    }
                    //if not found: create new record and add
                } else {
                    System.out.println("Enter interest rate:");
                    double interestRate = inputControl.getInput(sc, 0.0, null);
                    interestRateRecord.setEffectDate(effectDate);
                    interestRateRecord.setProductType(productTypeStr);
                    interestRateRecord.setCurrency(currency);
                    interestRateRecord.setInterestRate(interestRate);
                    interestRates.add(interestRateRecord);
                }
            } else
                System.out.println("Wrong product type entered");

            System.out.println("Continue?");
            System.out.println("1 - Yes");
            System.out.println("2 - No");
            stopCondition = inputControl.getInput(sc, 1, 2);

        } while (stopCondition != 2);
    }

    public void approveCreditRating(Scanner sc, InputControl inputControl, CustomerHandle customerHandle,
                                    RatingHandle ratingHandle, Map<String, Object> users, List<RatingUpdateRequest> requests) {

        // get active requests
        List<RatingUpdateRequest> activeRequests = requests.stream()
                .filter(RatingUpdateRequest::isActive)
                .collect(Collectors.toList());
        if (activeRequests.size() > 0) {

            System.out.printf("%-5s%20s%10s%12s%10s%10s\n", "IDs", "Time created", "Staff Id",
                    "Customer Id", "Current", "Proposed");
            //list all active requests
            for (RatingUpdateRequest request : activeRequests) System.out.println(request.toString());

            System.out.println("Select request Id to approve:");
            int requestId = inputControl.getInput(sc, 1, null);

            if (ratingHandle.findRequest(activeRequests, requestId) != null) {
                RatingUpdateRequest request = ratingHandle.findRequest(activeRequests, requestId);
                int customerId = request.getCustomerId();
                String customerUsername = customerHandle.findCustomer(users, customerId);
                if (customerUsername != null) {
                    Customer customer = (Customer) users.get(customerUsername);
                    customer.setCreditRating(request.getProposedRating());
                    System.out.println("New rating has been approved");

                    // turn off flag
                    request.setActive(false);

                } else System.out.println("Customer not found");
            } else System.out.println("Request Id not found");
        } else System.out.println("No active request");
    }

    public void approveStaffRegister(Scanner sc, InputControl inputControl, Map<String, Object> users) {

        // filter locked staffs
        List<Object> lockedStaffs = users.values()
                .stream()
                .filter(object -> object.getClass().getSimpleName().equals("Staff"))
                .filter(object -> ((Staff) object).getUserStatus() == UserStatus.LOCKED)
                .collect(Collectors.toList());
        if (lockedStaffs.size() > 0) {
            System.out.printf("%10s%-30s%-15s%-7s%-5s%-30s%-10s\n", "Staff IDs", "Name", "Person IDs", "Gender", "Age",
                    "Email", "User status");

            // display staffs
            for (Object staff : lockedStaffs) {
                Staff currentStaff = (Staff) staff;
                System.out.printf("%-10d%-30s%-15s%-7s%-5d%-30s%-10s\n", currentStaff.getStaffId(), currentStaff.getName(),
                        currentStaff.getPersonId(), currentStaff.getGender(), currentStaff.getAge(),
                        currentStaff.getEmail(), currentStaff.getUserStatus().toString());
            }

            // input staff Id
            System.out.println("Select staff ID:");
            int staffId = inputControl.getInput(sc, 1, null);

            // check if IDs in above list, return staff
            boolean isFound = false;
            Staff foundStaff = new Staff();
            for (Object staff : lockedStaffs) {
                if (((Staff) staff).getStaffId() == staffId) {
                    isFound = true;
                    foundStaff = (Staff) staff;
                    break;
                }
            }

            // approve/decline
            if (isFound) {
                System.out.println("SELECT AN OPTION");
                System.out.println("1. Approve");
                System.out.println("2. Decline");
                System.out.println("3. Return");
                int inputDecision = inputControl.getInput(sc, 1, 3);
                switch (inputDecision) {
                    case 1: {
                        foundStaff.setUserStatus(UserStatus.ACTIVE);
                        System.out.println("Staff No." + staffId + " has been activated");
                        break;
                    }
                    case 2: {
                        foundStaff.setUserStatus(UserStatus.INACTIVE);
                        System.out.println("Staff register " + staffId + " has been denied");
                        break;
                    }
                    default:
                        break;
                }
            } else System.out.println("Wrong staff Id");
        } else System.out.println("No pending staff register");
    }


}
