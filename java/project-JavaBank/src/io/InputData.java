package io;

import entity.*;

import handle.InputControl;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class InputData {
    public void readExcel(InputControl inputControl,
                          Map<String, Object> users,
                          List<Product> products,
                          List<InterestRate> interestRates,
                          List<ExchangeRate> exchangeRates,
                          List<Request> requests,
                          List<Transaction> transactions) {
        try {
            String pathProject = System.getProperty("user.dir");
            FileInputStream excelFile = new FileInputStream(new File(pathProject + "/src/io/data.xlsx"));
            Workbook workbook = new XSSFWorkbook(excelFile);
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // get customer records
            importCustomer(workbook, inputControl, users);

            // get staff records
            importStaff(inputControl, workbook, users);

            // get manager records
            importManager(inputControl, workbook, users);

            // get person records and match with users
            importPerson(workbook, users);

            //import interest rates
            importInterestRate(workbook, inputControl, interestRates);

            //import exchange rates
            importExchangeRate(workbook, exchangeRates);

            //get account records
            importAccount(workbook, inputControl, products);

            //get loan records
            importLoan(workbook, inputControl, products);

            //get saving records
            importSaving(workbook, inputControl, products);

            //load products to each customer
            loadProduct(users, products);

            workbook.close();
            System.out.println("Done importing.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("JavaBank Error: file not found.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("JavaBank Error: IO error.");
        }
    }

    private void importCustomer(Workbook workbook, InputControl inputControl, Map<String, Object> users) {
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
            customer.setCreditRating(inputControl.toCreditRating(creditRatingStr));
            String userStatusStr = currentRow.getCell(6) == null ? "" : currentRow.getCell(6).getStringCellValue();
            customer.setUserStatus(inputControl.toUserStatus(userStatusStr));
            users.put(customer.getUsername(), customer);
        }
    }

    public void importStaff(InputControl inputControl, Workbook workbook, Map<String, Object> users) {
        String sheetName = "Staff";
        Sheet datatypeSheet = workbook.getSheet(sheetName);
        Iterator<Row> iterator = datatypeSheet.iterator();
        Row firstRow = iterator.next();
        Cell firstCell = firstRow.getCell(0);
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            Staff staff = new Staff();
            staff.setUserRole(UserRole.STAFF);
            staff.setStaffId((int) (currentRow.getCell(0) == null ? 0 : currentRow.getCell(0).getNumericCellValue()));
            staff.setPersonId(currentRow.getCell(1) == null ? null : currentRow.getCell(1).getStringCellValue());
            staff.setUsername(currentRow.getCell(2) == null ? null : currentRow.getCell(2).getStringCellValue());
            staff.setPassword(currentRow.getCell(3) == null ? null : currentRow.getCell(3).getStringCellValue());
            staff.setEmail(currentRow.getCell(4) == null ? null : currentRow.getCell(4).getStringCellValue());
            staff.setBasicSalary(currentRow.getCell(5) == null ? 0 : currentRow.getCell(5).getNumericCellValue());
            staff.setRateOfBonus(currentRow.getCell(6) == null ? 0 : currentRow.getCell(6).getNumericCellValue());
            String userStatusStr = currentRow.getCell(7) == null ? "" : currentRow.getCell(7).getStringCellValue();
            staff.setUserStatus(inputControl.toUserStatus(userStatusStr));
            users.put(staff.getUsername(), staff);
        }
    }

    public void importManager(InputControl inputControl, Workbook workbook, Map<String, Object> users) {
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
            manager.setRateOfBonus(currentRow.getCell(5) == null ? 0 : currentRow.getCell(5).getNumericCellValue());
            String userStatusStr = currentRow.getCell(6) == null ? "" : currentRow.getCell(6).getStringCellValue();
            manager.setUserStatus(inputControl.toUserStatus(userStatusStr));
            users.put(manager.getUsername(), manager);
        }
    }

    public <T extends Object> void importPerson(Workbook workbook, Map<String, T> users) {
        String sheetName = "Person";
        Sheet datatypeSheet = workbook.getSheet(sheetName);
        Iterator<Row> iterator = datatypeSheet.iterator();
        Row firstRow = iterator.next();
        Cell firstCell = firstRow.getCell(0);
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
    }

    public void importInterestRate(Workbook workbook, InputControl inputControl, List<InterestRate> interestRates) {
        String sheetName = "interestRate";
        Sheet datatypeSheet = workbook.getSheet(sheetName);
        Iterator<Row> iterator = datatypeSheet.iterator();
        Row firstRow = iterator.next();
        Cell firstCell = firstRow.getCell(0);
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            InterestRate interestRate = new InterestRate();
            interestRate.setEffectDate(currentRow.getCell(0) == null ? null : currentRow.getCell(0).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            interestRate.setCurrency(currentRow.getCell(1) == null ? null : currentRow.getCell(1).getStringCellValue());
            String productTypeStr = currentRow.getCell(2) == null ? null : currentRow.getCell(2).getStringCellValue();
            interestRate.setProductType(inputControl.toProductType(productTypeStr));
            interestRate.setTenor(currentRow.getCell(3) == null ? null : (int) currentRow.getCell(3).getNumericCellValue());
            interestRate.setCreditRatingStr(currentRow.getCell(4) == null ? null : currentRow.getCell(4).getStringCellValue());
            interestRate.setInterestRate(currentRow.getCell(5) == null ? 0 : currentRow.getCell(5).getNumericCellValue());
            interestRates.add(interestRate);
        }
    }

    public void importExchangeRate(Workbook workbook, List<ExchangeRate> exchangeRates) {
        String sheetName = "exchangeRate";
        Sheet datatypeSheet = workbook.getSheet(sheetName);
        Iterator<Row> iterator = datatypeSheet.iterator();
        Row firstRow = iterator.next();
        Cell firstCell = firstRow.getCell(0);
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            ExchangeRate exchangeRate = new ExchangeRate();
            exchangeRate.setEffectDate(currentRow.getCell(0) == null ? null : currentRow.getCell(0).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            exchangeRate.setFromCurrency(currentRow.getCell(1) == null ? null : currentRow.getCell(1).getStringCellValue());
            exchangeRate.setToCurrency(currentRow.getCell(2) == null ? null : currentRow.getCell(2).getStringCellValue());
            exchangeRate.setExchangeRate(currentRow.getCell(3) == null ? 0 : currentRow.getCell(3).getNumericCellValue());
            exchangeRates.add(exchangeRate);
        }
    }

    public void importAccount(Workbook workbook, InputControl inputControl, List<Product> products) {
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
            account.setValueDate(currentRow.getCell(3) == null ? null : currentRow.getCell(3).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            account.setMaturityDate(currentRow.getCell(4) == null ? null : currentRow.getCell(4).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            account.setTenor(currentRow.getCell(5) == null ? null : (int) currentRow.getCell(5).getNumericCellValue());
            account.setCurrency(currentRow.getCell(6) == null ? null : currentRow.getCell(6).getStringCellValue());
            account.setBalance(currentRow.getCell(7) == null ? 0 : currentRow.getCell(7).getNumericCellValue());
            account.setConvertedBalance(currentRow.getCell(8) == null ? 0 : currentRow.getCell(8).getNumericCellValue());
            account.setInterestRate(currentRow.getCell(9) == null ? 0 : currentRow.getCell(9).getNumericCellValue());
            String productStatusStr = currentRow.getCell(10) == null ? null : currentRow.getCell(10).getStringCellValue();
            account.setProductStatus(inputControl.toProductStatus(productStatusStr));
            products.add(account);
        }
    }

    public void importLoan(Workbook workbook, InputControl inputControl, List<Product> products) {
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
            loan.setValueDate(currentRow.getCell(3) == null ? null : currentRow.getCell(3).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            loan.setMaturityDate(currentRow.getCell(4) == null ? null : currentRow.getCell(4).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            loan.setTenor(currentRow.getCell(5) == null ? null : (int) currentRow.getCell(5).getNumericCellValue());
            loan.setCurrency(currentRow.getCell(6) == null ? null : currentRow.getCell(6).getStringCellValue());
            loan.setBalance(currentRow.getCell(7) == null ? 0 : currentRow.getCell(7).getNumericCellValue());
            loan.setConvertedBalance(currentRow.getCell(8) == null ? 0 : currentRow.getCell(8).getNumericCellValue());
            loan.setInterestRate(currentRow.getCell(9) == null ? 0 : currentRow.getCell(9).getNumericCellValue());
            String productStatusStr = currentRow.getCell(10) == null ? null : currentRow.getCell(10).getStringCellValue();
            loan.setProductStatus(inputControl.toProductStatus(productStatusStr));
            //check maturitydate -> if matureed -> producstatus to INACTIVE
            products.add(loan);
        }
    }

    public void importSaving(Workbook workbook, InputControl inputControl, List<Product> products) {
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
            saving.setValueDate(currentRow.getCell(3) == null ? null : currentRow.getCell(3).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            saving.setMaturityDate(currentRow.getCell(4) == null ? null : currentRow.getCell(4).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            saving.setTenor(currentRow.getCell(5) == null ? null : (int) currentRow.getCell(5).getNumericCellValue());
            saving.setCurrency(currentRow.getCell(6) == null ? null : currentRow.getCell(6).getStringCellValue());
            saving.setBalance(currentRow.getCell(7) == null ? 0 : currentRow.getCell(7).getNumericCellValue());
            saving.setConvertedBalance(currentRow.getCell(8) == null ? 0 : currentRow.getCell(8).getNumericCellValue());
            saving.setInterestRate(currentRow.getCell(9) == null ? 0 : currentRow.getCell(9).getNumericCellValue());
            String productStatusStr = currentRow.getCell(10) == null ? null : currentRow.getCell(10).getStringCellValue();
            saving.setProductStatus(inputControl.toProductStatus(productStatusStr));
            //check maturitydate -> if matureed -> producstatus to INACTIVE
            products.add(saving);
        }
    }

    public void loadProduct(Map<String, Object> users, List<Product> products) {
        for (Map.Entry<String, Object> entry : users.entrySet()) {
            if (entry.getValue().getClass().getSimpleName().equals("Customer")) {
                String username = entry.getKey();
                Customer customer = (Customer) entry.getValue();
                List<Product> subProducts = new ArrayList<>();
                for (Product product : products) {
                    if (product.getCustomerId() == customer.getCustomerId())
                        subProducts.add(product);
                }
                customer.setProducts(subProducts);
                // update - overwrite customer with updated subproducts
                users.put(username, customer);
            }
        }
    }

}