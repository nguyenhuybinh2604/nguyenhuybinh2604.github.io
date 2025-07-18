package io;

import entity.*;

import handle.InputControl;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class DataIO {
    public void readExcel(InputControl inputControl,
                          Map<String, Object> users,
                          List<Product> products,
                          List<InterestRate> interestRates,
                          List<ExchangeRate> exchangeRates,
                          List<Transaction> transactions,
                          List<RatingUpdateRequest> ratingUpdateRequests) {
        try {
            String pathProject = System.getProperty("user.dir");
            FileInputStream excelFile = new FileInputStream((pathProject + "/src/io/data.xlsx"));
            Workbook workbook = new XSSFWorkbook(excelFile);

            DateTimeFormatter fmtLocalDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter fmtLocalDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            importCustomer(workbook, users);
            importStaff(workbook, users);
            importManager(workbook, users);
            importPerson(workbook, users);

            importInterestRate(workbook, fmtLocalDate, interestRates);
            importExchangeRate(workbook, fmtLocalDate, exchangeRates);

            importAccount(workbook, fmtLocalDate, products);
            importLoan(workbook, fmtLocalDate, products);
            importSaving(workbook, fmtLocalDate, products);

            loadProduct(users, products);
            importTransaction(workbook, fmtLocalDateTime, transactions);

            importRatingUpdateRequests(workbook, fmtLocalDateTime, ratingUpdateRequests);

            workbook.close();

            if (users.size() == 0) System.out.println("No user record");
            if (products.size() == 0) System.out.println("No product record");
            if (transactions.size() == 0) System.out.println("No transaction record");
            if (interestRates.size() == 0) System.out.println("No interest rate record");
            if (exchangeRates.size() == 0) System.out.println("No exchange rate record");
            System.out.println("Done importing");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("JavaBank Error: file not found.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("JavaBank Error: IO error.");
        }
    }

    private void importCustomer(Workbook workbook, Map<String, Object> users) {
        String sheetName = "Customer";
        Sheet datatypeSheet = workbook.getSheet(sheetName);
        Iterator<Row> iterator = datatypeSheet.iterator();
        Row firstRow = iterator.next();
        Cell firstCell = firstRow.getCell(0);
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            Customer customer = new Customer();
            customer.setUserRole(UserRole.CUSTOMER);
            customer.setCustomerId(
                    (int) (currentRow.getCell(0) == null ? 0 : currentRow.getCell(0).getNumericCellValue()));
            customer.setPersonId(currentRow.getCell(1) == null ? null : currentRow.getCell(1).getStringCellValue());
            customer.setUsername(currentRow.getCell(2) == null ? null : currentRow.getCell(2).getStringCellValue());
            customer.setPassword(currentRow.getCell(3) == null ? null : currentRow.getCell(3).getStringCellValue());
            customer.setEmail(currentRow.getCell(4) == null ? null : currentRow.getCell(4).getStringCellValue());
            String creditRatingStr = currentRow.getCell(5) == null ? "" : currentRow.getCell(5).getStringCellValue();
            customer.setCreditRating(creditRatingStr);
            String userStatusStr = currentRow.getCell(6) == null ? "" : currentRow.getCell(6).getStringCellValue();
            customer.setUserStatus(userStatusStr);

            users.put(customer.getUsername(), customer);
        }
    }

    private void importStaff(Workbook workbook, Map<String, Object> users) {
        String sheetName = "Staff";
        Sheet datatypeSheet = workbook.getSheet(sheetName);
        Iterator<Row> iterator = datatypeSheet.iterator();
        Row firstRow = iterator.next();
        Cell firstCell = firstRow.getCell(0);
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            Staff staff = new Staff();
            staff.setUserRole(UserRole.STAFF);
            staff.setStaffId(currentRow.getCell(0) == null ? 0 : (int) currentRow.getCell(0).getNumericCellValue());
            staff.setPersonId(currentRow.getCell(1) == null ? null : currentRow.getCell(1).getStringCellValue());
            staff.setUsername(currentRow.getCell(2) == null ? null : currentRow.getCell(2).getStringCellValue());
            staff.setPassword(currentRow.getCell(3) == null ? null : currentRow.getCell(3).getStringCellValue());
            staff.setEmail(currentRow.getCell(4) == null ? null : currentRow.getCell(4).getStringCellValue());
            staff.setBasicSalary(currentRow.getCell(5) == null ? 0.0 : currentRow.getCell(5).getNumericCellValue());
            staff.setRank(currentRow.getCell(6) == null ? null : (int) currentRow.getCell(6).getNumericCellValue());
            staff.setBonus(currentRow.getCell(7) == null ? 0.0 : currentRow.getCell(7).getNumericCellValue());
            String userStatusStr = currentRow.getCell(8) == null ? "" : currentRow.getCell(8).getStringCellValue();
            staff.setUserStatus(userStatusStr);
            users.put(staff.getUsername(), staff);
        }
    }

    private void importManager(Workbook workbook, Map<String, Object> users) {
        String sheetName = "Manager";
        Sheet datatypeSheet = workbook.getSheet(sheetName);
        Iterator<Row> iterator = datatypeSheet.iterator();
        Row firstRow = iterator.next();
        Cell firstCell = firstRow.getCell(0);
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            Manager manager = new Manager();
            manager.setUserRole(UserRole.MANAGER);
            manager.setPersonId(currentRow.getCell(0) == null ? null : currentRow.getCell(0).getStringCellValue());
            manager.setUsername(currentRow.getCell(1) == null ? null : currentRow.getCell(1).getStringCellValue());
            manager.setPassword(currentRow.getCell(2) == null ? null : currentRow.getCell(2).getStringCellValue());
            manager.setEmail(currentRow.getCell(3) == null ? null : currentRow.getCell(3).getStringCellValue());
            manager.setBasicSalary(currentRow.getCell(4) == null ? 0 : currentRow.getCell(4).getNumericCellValue());
            manager.setBonus(currentRow.getCell(5) == null ? 0 : currentRow.getCell(5).getNumericCellValue());
            String userStatusStr = currentRow.getCell(6) == null ? "" : currentRow.getCell(6).getStringCellValue();
            manager.setUserStatus(userStatusStr);
            users.put(manager.getUsername(), manager);
        }
    }

    private <T extends Object> void importPerson(Workbook workbook, Map<String, T> users) {
        String sheetName = "Person";
        Sheet datatypeSheet = workbook.getSheet(sheetName);
        Iterator<Row> iterator = datatypeSheet.iterator();
        Row firstRow = iterator.next();
        Cell firstCell = firstRow.getCell(0);
        if (users != null && users.size() > 0) {
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                String personId = currentRow.getCell(0) == null ? null : currentRow.getCell(0).getStringCellValue();
                if (personId != null) {
                    for (T user : users.values()) {
                        if (((Person) user).getPersonId().equals(personId)) {
                            ((Person) user).setName(
                                    currentRow.getCell(1) == null ? null : currentRow.getCell(1).getStringCellValue());
                            ((Person) user).setGender(
                                    currentRow.getCell(2) == null ? null : currentRow.getCell(2).getStringCellValue());
                            ((Person) user).setAge(
                                    (int) (currentRow.getCell(3) == null ? 0
                                            : currentRow.getCell(3).getNumericCellValue()));
                            ((Person) user).setAddress(
                                    currentRow.getCell(4) == null ? null : currentRow.getCell(4).getStringCellValue());
                            // break after matched user
                            break;
                        }
                    }
                }
            }
        } else System.out.println("No record");
    }

    private void importInterestRate(Workbook workbook, DateTimeFormatter formatter, List<InterestRate> interestRates) {
        String sheetName = "interestRate";
        Sheet datatypeSheet = workbook.getSheet(sheetName);
        Iterator<Row> iterator = datatypeSheet.iterator();
        Row firstRow = iterator.next();
        Cell firstCell = firstRow.getCell(0);
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            InterestRate interestRate = new InterestRate();
            interestRate.setEffectDate(currentRow.getCell(0) == null ? null : LocalDate.parse(currentRow.getCell(0).getStringCellValue(), formatter));
            interestRate.setCurrency(currentRow.getCell(1) == null ? null : currentRow.getCell(1).getStringCellValue());
            String productTypeStr = currentRow.getCell(2) == null ? null : currentRow.getCell(2).getStringCellValue();
            interestRate.setProductType(productTypeStr);
            interestRate.setTenor(currentRow.getCell(3) == null ? null : (int) currentRow.getCell(3).getNumericCellValue());
            interestRate.setCreditRatingStr(currentRow.getCell(4) == null ? null : currentRow.getCell(4).getStringCellValue());
            interestRate.setInterestRate(currentRow.getCell(5) == null ? 0 : currentRow.getCell(5).getNumericCellValue());
            interestRates.add(interestRate);
        }
    }

    private void importExchangeRate(Workbook workbook, DateTimeFormatter formatter, List<ExchangeRate> exchangeRates) {
        String sheetName = "exchangeRate";
        Sheet datatypeSheet = workbook.getSheet(sheetName);
        Iterator<Row> iterator = datatypeSheet.iterator();
        Row firstRow = iterator.next();
        Cell firstCell = firstRow.getCell(0);
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            ExchangeRate exchangeRate = new ExchangeRate();
            if (currentRow.getCell(0) != null && !currentRow.getCell(0).getStringCellValue().isEmpty())
            exchangeRate.setEffectDate(LocalDate.parse(currentRow.getCell(0).getStringCellValue(), formatter));
            exchangeRate.setFromCurrency(currentRow.getCell(1) == null ? null : currentRow.getCell(1).getStringCellValue());
            exchangeRate.setToCurrency(currentRow.getCell(2) == null ? null : currentRow.getCell(2).getStringCellValue());
            exchangeRate.setExchangeRate(currentRow.getCell(3) == null ? 0 : currentRow.getCell(3).getNumericCellValue());
            exchangeRates.add(exchangeRate);
        }
    }

    private void importAccount(Workbook workbook, DateTimeFormatter formatter, List<Product> products) {
        String sheetName = "Account";
        Sheet datatypeSheet = workbook.getSheet(sheetName);
        Iterator<Row> iterator = datatypeSheet.iterator();
        Row firstRow = iterator.next();
        Cell firstCell = firstRow.getCell(0);
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            Account account = new Account();
            account.setProductType(ProductType.ACCOUNT);
            account.setProductId((int) (currentRow.getCell(0) == null ? 0 : currentRow.getCell(0).getNumericCellValue()));
            account.setCustomerId((int) (currentRow.getCell(1) == null ? 0 : currentRow.getCell(1).getNumericCellValue()));
            account.setStaffId(currentRow.getCell(2) == null ? null : (int) currentRow.getCell(2).getNumericCellValue());
            account.setValueDate(currentRow.getCell(3) == null ? null : LocalDate.parse(currentRow.getCell(3).getStringCellValue(), formatter));
            if (currentRow.getCell(4) != null && !currentRow.getCell(4).getStringCellValue().isEmpty())
                account.setMaturityDate(LocalDate.parse(currentRow.getCell(4).getStringCellValue(), formatter));
            account.setTenor(currentRow.getCell(5) == null ? null : (int) currentRow.getCell(5).getNumericCellValue());
            account.setCurrency(currentRow.getCell(6) == null ? null : currentRow.getCell(6).getStringCellValue());
            account.setBalance(currentRow.getCell(7) == null ? 0 : currentRow.getCell(7).getNumericCellValue());
            account.setConvertedBalance(currentRow.getCell(8) == null ? 0 : currentRow.getCell(8).getNumericCellValue());
            account.setInterestRate(currentRow.getCell(9) == null ? 0 : currentRow.getCell(9).getNumericCellValue());
            String productStatusStr = currentRow.getCell(10) == null ? null : currentRow.getCell(10).getStringCellValue();
            account.setProductStatus(productStatusStr);
            products.add(account);

        }
    }

    private void importLoan(Workbook workbook, DateTimeFormatter formatter, List<Product> products) {
        String sheetName = "Loan";
        Sheet datatypeSheet = workbook.getSheet(sheetName);
        Iterator<Row> iterator = datatypeSheet.iterator();
        Row firstRow = iterator.next();
        Cell firstCell = firstRow.getCell(0);
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            Loan loan = new Loan();
            loan.setProductType(ProductType.LOAN);
            loan.setProductId((int) (currentRow.getCell(0) == null ? 0 : currentRow.getCell(0).getNumericCellValue()));
            loan.setCustomerId((int) (currentRow.getCell(1) == null ? 0 : currentRow.getCell(1).getNumericCellValue()));
            loan.setStaffId(currentRow.getCell(2) == null ? null : (int) currentRow.getCell(2).getNumericCellValue());
            if (currentRow.getCell(3) != null && !currentRow.getCell(3).getStringCellValue().isEmpty()) {
                loan.setValueDate(LocalDate.parse(currentRow.getCell(3).getStringCellValue(), formatter));
            }
            if (currentRow.getCell(4) != null && !currentRow.getCell(4).getStringCellValue().isEmpty()) {
                loan.setMaturityDate(LocalDate.parse(currentRow.getCell(4).getStringCellValue(), formatter));
            }
            loan.setTenor(currentRow.getCell(5) == null ? null : (int) currentRow.getCell(5).getNumericCellValue());
            loan.setCurrency(currentRow.getCell(6) == null ? null : currentRow.getCell(6).getStringCellValue());
            loan.setBalance(currentRow.getCell(7) == null ? 0 : currentRow.getCell(7).getNumericCellValue());
            loan.setConvertedBalance(currentRow.getCell(8) == null ? 0 : currentRow.getCell(8).getNumericCellValue());
            loan.setInterestRate(currentRow.getCell(9) == null ? 0 : currentRow.getCell(9).getNumericCellValue());
            String productStatusStr = currentRow.getCell(10) == null ? null : currentRow.getCell(10).getStringCellValue();
            loan.setProductStatus(productStatusStr);

            //check maturitydate of ACTIVE loans -> if matured -> to INACTIVE
            if (loan.getProductStatus() == ProductStatus.ACTIVE && loan.getMaturityDate().isBefore(LocalDate.now()))
                loan.setProductStatus(ProductStatus.INACTIVE);
            products.add(loan);

        }
    }

    private void importSaving(Workbook workbook, DateTimeFormatter formatter, List<Product> products) {
        String sheetName = "Saving";
        Sheet datatypeSheet = workbook.getSheet(sheetName);
        Iterator<Row> iterator = datatypeSheet.iterator();
        Row firstRow = iterator.next();
        Cell firstCell = firstRow.getCell(0);
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            Saving saving = new Saving();
            saving.setProductType(ProductType.SAVING);
            saving.setProductId((int) (currentRow.getCell(0) == null ? 0 : currentRow.getCell(0).getNumericCellValue()));
            saving.setCustomerId((int) (currentRow.getCell(1) == null ? 0 : currentRow.getCell(1).getNumericCellValue()));
            saving.setStaffId(currentRow.getCell(2) == null ? null : (int) currentRow.getCell(2).getNumericCellValue());
            saving.setValueDate(currentRow.getCell(3) == null ? null : LocalDate.parse(currentRow.getCell(3).getStringCellValue(), formatter));
            saving.setMaturityDate(currentRow.getCell(4) == null ? null : LocalDate.parse(currentRow.getCell(4).getStringCellValue(), formatter));
            saving.setTenor(currentRow.getCell(5) == null ? null : (int) currentRow.getCell(5).getNumericCellValue());
            saving.setCurrency(currentRow.getCell(6) == null ? null : currentRow.getCell(6).getStringCellValue());
            saving.setBalance(currentRow.getCell(7) == null ? 0 : currentRow.getCell(7).getNumericCellValue());
            saving.setConvertedBalance(currentRow.getCell(8) == null ? 0 : currentRow.getCell(8).getNumericCellValue());
            saving.setInterestRate(currentRow.getCell(9) == null ? 0 : currentRow.getCell(9).getNumericCellValue());
            String productStatusStr = currentRow.getCell(10) == null ? null : currentRow.getCell(10).getStringCellValue();
            saving.setProductStatus(productStatusStr);

            //check maturitydate of ACTIVE savings -> if matured -> to INACTIVE
            if (saving.getProductStatus() == ProductStatus.ACTIVE && saving.getMaturityDate().isBefore(LocalDate.now()))
                saving.setProductStatus(ProductStatus.INACTIVE);
            products.add(saving);
        }
    }

    private void importTransaction(Workbook workbook, DateTimeFormatter formatter, List<Transaction> transactions) {
        String sheetName = "transactionLog";
        Sheet datatypeSheet = workbook.getSheet(sheetName);
        Iterator<Row> iterator = datatypeSheet.iterator();
        Row firstRow = iterator.next();
        Cell firstCell = firstRow.getCell(0);
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            Transaction transaction = new Transaction();
            if (currentRow.getCell(0) != null)
                transaction.setTransactionId((int) currentRow.getCell(0).getNumericCellValue());
            if (currentRow.getCell(1) != null && !currentRow.getCell(1).getStringCellValue().isEmpty())
                transaction.setTransactionTime(LocalDateTime.parse(currentRow.getCell(1).getStringCellValue(), formatter));
            if (currentRow.getCell(2) != null)
                transaction.setTransactionType(currentRow.getCell(2).getStringCellValue());
            if (currentRow.getCell(3) != null)
                transaction.setAccountId((int) currentRow.getCell(3).getNumericCellValue());
            if (currentRow.getCell(4) != null)
                transaction.setCustomerId((int) currentRow.getCell(4).getNumericCellValue());
            transaction.setCurrency(currentRow.getCell(5) == null ? null : currentRow.getCell(5).getStringCellValue());
            if (currentRow.getCell(6) != null) transaction.setCredit(currentRow.getCell(6).getNumericCellValue());
            if (currentRow.getCell(7) != null) transaction.setDebit(currentRow.getCell(7).getNumericCellValue());
            if (currentRow.getCell(8) != null)
                transaction.setConvertedCredit(currentRow.getCell(8).getNumericCellValue());
            if (currentRow.getCell(9) != null)
                transaction.setConvertedDebit(currentRow.getCell(9).getNumericCellValue());

            transactions.add(transaction);
        }
    }

    private void importRatingUpdateRequests(Workbook workbook, DateTimeFormatter formatter, List<RatingUpdateRequest> ratingUpdateRequests) {
        String sheetName = "ratingUpdate";
        Sheet datatypeSheet = workbook.getSheet(sheetName);
        Iterator<Row> iterator = datatypeSheet.iterator();
        Row firstRow = iterator.next();
        Cell firstCell = firstRow.getCell(0);
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            RatingUpdateRequest ratingUpdateRequest = new RatingUpdateRequest();
            if (currentRow.getCell(0) != null)
                ratingUpdateRequest.setRequestId((int) currentRow.getCell(0).getNumericCellValue());
            if (currentRow.getCell(1) != null && !currentRow.getCell(1).getStringCellValue().isEmpty())
                ratingUpdateRequest.setRequestCreation(LocalDateTime.parse(currentRow.getCell(1).getStringCellValue(), formatter));
            if (currentRow.getCell(2) != null)
                ratingUpdateRequest.setStaffId((int) currentRow.getCell(2).getNumericCellValue());
            if (currentRow.getCell(3) != null)
                ratingUpdateRequest.setCustomerId((int) currentRow.getCell(3).getNumericCellValue());
            String currentRatingStr = "";
            if (currentRow.getCell(4) != null)
                currentRatingStr = currentRow.getCell(4).getStringCellValue();
            ratingUpdateRequest.setCurrentRating(currentRatingStr);
            String proposedRatingStr = "";
            if (currentRow.getCell(5) != null)
                proposedRatingStr = currentRow.getCell(5).getStringCellValue();
            ratingUpdateRequest.setProposedRating(proposedRatingStr);
//            String isActiveStr = "false";
            if (currentRow.getCell(6) != null)
//                isActiveStr=currentRow.getCell(6).getStringCellValue();
                ratingUpdateRequest.setActive(currentRow.getCell(6).getBooleanCellValue());

            ratingUpdateRequests.add(ratingUpdateRequest);

        }
    }

    // load to each customer
    private void loadProduct(Map<String, Object> users, List<Product> products) {
        if (users != null && users.size() > 0) {
            for (Map.Entry<String, Object> entry : users.entrySet()) {
                if (entry.getValue().getClass().getSimpleName().equals("Customer")) {
                    String username = entry.getKey();
                    Customer customer = (Customer) entry.getValue();
                    if (products != null && products.size() > 0) {
                        List<Product> filteredProducts = products.stream()
                                .filter(o -> o.getCustomerId() == customer.getCustomerId())
                                .collect(Collectors.toList());
                        customer.setProducts(filteredProducts); // can set null list

                        // update - overwrite customer with updated products
                        users.put(username, customer);
                    }
                }
            }
        }
    }

    // load to each customer
    private void loadTransaction(Map<String, Object> users, List<Transaction> transactions) {
        if (users != null && users.size() > 0) {
            for (Map.Entry<String, Object> entry : users.entrySet()) {
                if (entry.getValue().getClass().getSimpleName().equals("Customer")) {
                    String username = entry.getKey();
                    Customer customer = (Customer) entry.getValue();
                    if (transactions != null && transactions.size() > 0) {
                        List<Transaction> filteredTransactions = transactions.stream()
                                .filter(o -> o.getCustomerId() == customer.getCustomerId())
                                .collect(Collectors.toList());
                        customer.setTransactions(filteredTransactions); // can set null list

                        // update - overwrite customer with updated products
                        users.put(username, customer);
                    }
                }
            }
        }
    }

    public void writeExcel(
            Map<String, Object> users,
            List<Product> products,
            List<InterestRate> interestRates,
            List<ExchangeRate> exchangeRates,
            List<Transaction> transactions,
            List<RatingUpdateRequest> ratingUpdateRequests) {
        try {
            String pathProject = System.getProperty("user.dir");
            DateTimeFormatter fmtLocalDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter fmtLocalDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            File file = new File(pathProject + "/src/io/data.xlsx");

            //Check if workbook is open
            Workbook checkWorkbook = null;
            boolean isOpen = true;

            // Loop until the workbook is closed
            while (isOpen) {
                try {
                    // Try to create a new workbook from the file
                    checkWorkbook = WorkbookFactory.create(file);
                    isOpen = false; // workbook is not open
                } catch (IOException e) {
                    // If the workbook is open, catch the IOException and wait for it to close
                    System.out.println("Workbook is open. Waiting for it to close...");
                    try {
                        Thread.sleep(5000); // wait for 1 second
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                } catch (InvalidFormatException e) {
                    e.printStackTrace();
                }
            }

            // Open the existing workbook and sheet
            FileInputStream inputStream = new FileInputStream(pathProject + "/src/io/data.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            FileOutputStream outputStream = new FileOutputStream(pathProject + "/src/io/data.xlsx");

            exportExchangeRate(workbook, exchangeRates, fmtLocalDate);
            exportInterestRate(workbook, interestRates, fmtLocalDate);

            exportProduct(workbook, products, fmtLocalDate, ProductType.ACCOUNT, "Account");
            exportProduct(workbook, products, fmtLocalDate, ProductType.LOAN, "Loan");
            exportProduct(workbook, products, fmtLocalDate, ProductType.SAVING, "Saving");

            exportCustomer(workbook, users);
            exportStaff(workbook, users);
            exportManager(workbook, users);
            exportPerson(workbook, users);

            exportTransaction(workbook, transactions, fmtLocalDateTime);
            exportRatingUpdateRequest(workbook, ratingUpdateRequests, fmtLocalDateTime);

            // Write the workbook to the file
            workbook.write(outputStream);
            workbook.close();
            System.out.println("Done exporting");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("JavaBank Error: file not found.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("JavaBank Error: IO error.");
        }
    }

    private void clearSheet(XSSFSheet sheet) {

        // Clear all rows from the 2nd row downward
        for (int j = sheet.getLastRowNum(); j >= 1; j--) {
            XSSFRow row = sheet.getRow(j);
            if (row != null) {
                sheet.removeRow(row);
            }
        }
    }

    private void exportExchangeRate(XSSFWorkbook workbook, List<ExchangeRate> exchangeRates,
                                    DateTimeFormatter fmt) throws IOException {
        XSSFSheet sheet = workbook.getSheet("exchangeRate");

        // Clear all rows from the 2nd row downward
        clearSheet(sheet);

        // Write the list to the sheet
        int rowCount = 0;
        for (ExchangeRate exchangeRate : exchangeRates) {
            XSSFRow sheetRow = sheet.createRow(++rowCount);
            XSSFCell cell1 = sheetRow.createCell(0);
            if (exchangeRate.getEffectDate() != null) cell1.setCellValue(exchangeRate.getEffectDate().format(fmt));
            XSSFCell cell2 = sheetRow.createCell(1);
            if (exchangeRate.getFromCurrency() != null) cell2.setCellValue(exchangeRate.getFromCurrency());
            XSSFCell cell3 = sheetRow.createCell(2);
            if (exchangeRate.getToCurrency() != null) cell3.setCellValue(exchangeRate.getToCurrency());
            XSSFCell cell4 = sheetRow.createCell(3);
            cell4.setCellValue(exchangeRate.getExchangeRate());
        }

    }

    private void exportInterestRate(XSSFWorkbook workbook, List<InterestRate> interestRates,
                                    DateTimeFormatter fmt) throws IOException {
        XSSFSheet sheet = workbook.getSheet("interestRate");

        // Clear all rows from the 2nd row downward
        clearSheet(sheet);

        // Write the list to the sheet
        int rowCount = 0;
        for (InterestRate interestRate : interestRates) {
            XSSFRow sheetRow = sheet.createRow(++rowCount);
            XSSFCell cell1 = sheetRow.createCell(0);
            if (interestRate.getEffectDate() != null) cell1.setCellValue(interestRate.getEffectDate().format(fmt));
            XSSFCell cell2 = sheetRow.createCell(1);
            if (interestRate.getCurrency() != null) cell2.setCellValue(interestRate.getCurrency());
            XSSFCell cell3 = sheetRow.createCell(2);
            if (interestRate.getProductType() != null) cell3.setCellValue(interestRate.getProductType().toString());
            XSSFCell cell4 = sheetRow.createCell(3);
            if (interestRate.getTenor() != null) cell4.setCellValue(interestRate.getTenor());
            XSSFCell cell5 = sheetRow.createCell(4);
            if (interestRate.getCreditRatingStr() != null) cell5.setCellValue(interestRate.getCreditRatingStr());
            XSSFCell cell6 = sheetRow.createCell(5);
            cell6.setCellValue(interestRate.getInterestRate());
        }
    }

    private void exportProduct(XSSFWorkbook workbook, List<Product> products,
                               DateTimeFormatter fmt, ProductType productType, String sheetName) throws IOException {
        if (products != null && products.size() > 0) {
            XSSFSheet sheet = workbook.getSheet(sheetName);

            // Clear all rows from the 2nd row downward
            clearSheet(sheet);

            // Get product list
            List<Product> filteredAccounts = products.stream()
                    .filter(o -> o.getProductType() == productType)
                    .collect(Collectors.toList());

            // Write the list to the sheet
            int rowCount = 0;
            for (Product product : filteredAccounts) {
                XSSFRow sheetRow = sheet.createRow(++rowCount);
                XSSFCell cell1 = sheetRow.createCell(0);
                cell1.setCellValue(product.getProductId());
                XSSFCell cell2 = sheetRow.createCell(1);
                cell2.setCellValue(product.getCustomerId());
                XSSFCell cell3 = sheetRow.createCell(2);
                if (product.getStaffId() != null) cell3.setCellValue(product.getStaffId());
                XSSFCell cell4 = sheetRow.createCell(3);
                if (product.getValueDate() != null) cell4.setCellValue(product.getValueDate().format(fmt));
                XSSFCell cell5 = sheetRow.createCell(4);
                if (product.getMaturityDate() != null) cell5.setCellValue(product.getMaturityDate().format(fmt));
                XSSFCell cell6 = sheetRow.createCell(5);
                if (product.getTenor() != null) cell6.setCellValue(product.getTenor());
                XSSFCell cell7 = sheetRow.createCell(6);
                if (product.getCurrency() != null) cell7.setCellValue(product.getCurrency());
                XSSFCell cell8 = sheetRow.createCell(7);
                cell8.setCellValue(product.getBalance());
                XSSFCell cell9 = sheetRow.createCell(8);
                cell9.setCellValue(product.getConvertedBalance());
                XSSFCell cell10 = sheetRow.createCell(9);
                cell10.setCellValue(product.getInterestRate());
                XSSFCell cell11 = sheetRow.createCell(10);
                if (product.getProductStatus() != null) cell11.setCellValue(product.getProductStatus().toString());
            }
        }
    }

    private void exportCustomer(XSSFWorkbook workbook, Map<String, Object> users)
            throws IOException {
        XSSFSheet sheet = workbook.getSheet("Customer");

        // Clear all rows from the 2nd row downward
        clearSheet(sheet);

        // Filter users
        Map<String, Object> filteredUsers = users.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getClass().getSimpleName().equals("Customer"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Write customers
        int rowCount = 0;
        for (Object user : filteredUsers.values()) {
            XSSFRow sheetRow = sheet.createRow(++rowCount);

            XSSFCell cell1 = sheetRow.createCell(0);
            cell1.setCellValue(((Customer) user).getCustomerId());
            XSSFCell cell2 = sheetRow.createCell(1);
            cell2.setCellValue(((Customer) user).getPersonId());
            XSSFCell cell3 = sheetRow.createCell(2);
            cell3.setCellValue(((Customer) user).getUsername());
            XSSFCell cell4 = sheetRow.createCell(3);
            cell4.setCellValue(((Customer) user).getPassword());
            XSSFCell cell5 = sheetRow.createCell(4);
            cell5.setCellValue(((Customer) user).getEmail());
            XSSFCell cell6 = sheetRow.createCell(5);
            if (((Customer) user).getCreditRating() != null)
                cell6.setCellValue(((Customer) user).getCreditRating().toString());
            XSSFCell cell7 = sheetRow.createCell(6);
            if (((Customer) user).getUserStatus() != null)
                cell7.setCellValue(((Customer) user).getUserStatus().toString());

        }
    }

    private void exportStaff(XSSFWorkbook workbook, Map<String, Object> users)
            throws IOException {
        XSSFSheet sheet = workbook.getSheet("Staff");

        // Clear all rows from the 2nd row downward
        clearSheet(sheet);

        // Filter users
        Map<String, Object> filteredUsers = users.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getClass().getSimpleName().equals("Staff"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Write staffs
        int rowCount = 0;
        for (Object user : filteredUsers.values()) {
            XSSFRow sheetRow = sheet.createRow(++rowCount);

            XSSFCell cell1 = sheetRow.createCell(0);
            cell1.setCellValue(((Staff) user).getStaffId());
            XSSFCell cell2 = sheetRow.createCell(1);
            cell2.setCellValue(((Staff) user).getPersonId());
            XSSFCell cell3 = sheetRow.createCell(2);
            cell3.setCellValue(((Staff) user).getUsername());
            XSSFCell cell4 = sheetRow.createCell(3);
            cell4.setCellValue(((Staff) user).getPassword());
            XSSFCell cell5 = sheetRow.createCell(4);
            cell5.setCellValue(((Staff) user).getEmail());
            XSSFCell cell6 = sheetRow.createCell(5);
            cell6.setCellValue(((Staff) user).getBasicSalary());
            XSSFCell cell7 = sheetRow.createCell(6);
            if (((Staff) user).getRank() != 0) cell7.setCellValue(((Staff) user).getRank());
            XSSFCell cell8 = sheetRow.createCell(7);
            if (((Staff) user).getBonus() != 0) cell8.setCellValue(((Staff) user).getBonus());
            XSSFCell cell9 = sheetRow.createCell(8);
            cell9.setCellValue(((Staff) user).getUserStatus().toString());
        }
    }

    private void exportManager(XSSFWorkbook workbook, Map<String, Object> users)
            throws IOException {
        XSSFSheet sheet = workbook.getSheet("Manager");

        // Clear all rows from the 2nd row downward
        clearSheet(sheet);

        // Filter users
        Map<String, Object> filteredUsers = users.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getClass().getSimpleName().equals("Manager"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Write managers
        int rowCount = 0;
        for (Object user : filteredUsers.values()) {
            XSSFRow sheetRow = sheet.createRow(++rowCount);

            XSSFCell cell1 = sheetRow.createCell(0);
            cell1.setCellValue(((Manager) user).getPersonId());
            XSSFCell cell2 = sheetRow.createCell(1);
            cell2.setCellValue(((Manager) user).getUsername());
            XSSFCell cell3 = sheetRow.createCell(2);
            cell3.setCellValue(((Manager) user).getPassword());
            XSSFCell cell4 = sheetRow.createCell(3);
            cell4.setCellValue(((Manager) user).getEmail());
            XSSFCell cell5 = sheetRow.createCell(4);
            cell5.setCellValue(((Manager) user).getBasicSalary());
            XSSFCell cell6 = sheetRow.createCell(5);
            cell6.setCellValue(((Manager) user).getBonus());
            XSSFCell cell7 = sheetRow.createCell(6);
            cell7.setCellValue(((Manager) user).getUserStatus().toString());
        }
    }

    private void exportPerson(XSSFWorkbook workbook, Map<String, Object> users)
            throws IOException {
        XSSFSheet sheet = workbook.getSheet("Person");

        // Clear all rows from the 2nd row downward
        clearSheet(sheet);

        // Write persons
        int rowCount = 0;
        for (Object user : users.values()) {
            XSSFRow sheetRow = sheet.createRow(++rowCount);

            XSSFCell cell1 = sheetRow.createCell(0);
            cell1.setCellValue(((Person) user).getPersonId());
            XSSFCell cell2 = sheetRow.createCell(1);
            cell2.setCellValue(((Person) user).getName());
            XSSFCell cell3 = sheetRow.createCell(2);
            cell3.setCellValue(((Person) user).getGender());
            XSSFCell cell4 = sheetRow.createCell(3);
            cell4.setCellValue(((Person) user).getAge());
            XSSFCell cell5 = sheetRow.createCell(4);
            cell5.setCellValue(((Person) user).getAddress());
        }
    }

    private void exportTransaction(XSSFWorkbook workbook, List<Transaction> transactions,
                                   DateTimeFormatter fmt) {
        XSSFSheet sheet = workbook.getSheet("transactionLog");

        // Clear all rows from the 2nd row downward
        clearSheet(sheet);

        // Write the list to the sheet
        int rowCount = 0;
        if (transactions.size() > 0) {
            for (Transaction transaction : transactions) {
                XSSFRow sheetRow = sheet.createRow(++rowCount);

                XSSFCell cell1 = sheetRow.createCell(0);
                cell1.setCellValue(transaction.getTransactionId());
                XSSFCell cell2 = sheetRow.createCell(1);
                if (transaction.getTransactionTime() != null)
                    cell2.setCellValue(transaction.getTransactionTime().format(fmt));
                XSSFCell cell3 = sheetRow.createCell(2);
                if (transaction.getTransactionType() != null)
                    cell3.setCellValue(transaction.getTransactionType().toString());
                XSSFCell cell4 = sheetRow.createCell(3);
                cell4.setCellValue(transaction.getAccountId());
                XSSFCell cell5 = sheetRow.createCell(4);
                cell5.setCellValue(transaction.getCustomerId());
                XSSFCell cell6 = sheetRow.createCell(5);
                cell6.setCellValue(transaction.getCurrency());
                XSSFCell cell7 = sheetRow.createCell(6);
                cell7.setCellValue(transaction.getCredit());
                XSSFCell cell8 = sheetRow.createCell(7);
                cell8.setCellValue(transaction.getDebit());
                XSSFCell cell9 = sheetRow.createCell(8);
                cell9.setCellValue(transaction.getConvertedCredit());
                XSSFCell cell10 = sheetRow.createCell(9);
                cell10.setCellValue(transaction.getConvertedDebit());
            }
        }
    }

    private void exportRatingUpdateRequest(XSSFWorkbook workbook, List<RatingUpdateRequest> requests,
                                           DateTimeFormatter fmt) {
        XSSFSheet sheet = workbook.getSheet("ratingUpdate");

        // Clear all rows from the 2nd row downward
        clearSheet(sheet);

        // Write the list to the sheet
        int rowCount = 0;
        if (requests.size() > 0) {
            for (RatingUpdateRequest request : requests) {
                XSSFRow sheetRow = sheet.createRow(++rowCount);

                XSSFCell cell1 = sheetRow.createCell(0);
                cell1.setCellValue(request.getRequestId());
                XSSFCell cell2 = sheetRow.createCell(1);
                if (request.getRequestCreation() != null)
                    cell2.setCellValue(request.getRequestCreation().format(fmt));
                XSSFCell cell3 = sheetRow.createCell(2);
                cell3.setCellValue(request.getStaffId());
                XSSFCell cell4 = sheetRow.createCell(3);
                cell4.setCellValue(request.getCustomerId());
                XSSFCell cell5 = sheetRow.createCell(4);
                cell5.setCellValue(request.getCurrentRating().toString());
                XSSFCell cell6 = sheetRow.createCell(5);
                cell6.setCellValue(request.getProposedRating().toString());
                XSSFCell cell7 = sheetRow.createCell(6);
                cell7.setCellValue(request.isActive());
            }
        }
    }
}
