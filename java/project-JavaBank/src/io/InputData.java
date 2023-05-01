package io;

import entity.Account;
import entity.Customer;
import entity.IProduct;
import entity.Loan;
import entity.Manager;
import entity.Person;
import entity.Request;
import entity.Saving;
import entity.Staff;
import entity.Transaction;
import entity.UserRole;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class InputData {
    public void readExcel(Map<String, Object> users,
            List<Loan> loans,
            List<Saving> savings,
            List<Account> accounts,
            List<IProduct> products,
            List<Request> requests,
            List<Transaction> transactions) {
        try {
            String pathProject = System.getProperty("user.dir");
            FileInputStream excelFile = new FileInputStream(new File(pathProject + "/src/io/data.xlsx"));
            Workbook workbook = new XSSFWorkbook(excelFile);
            // DataFormatter fmt = new DataFormatter();

            // xoa trang map users de import data
            users.clear();
            // get customer records
            importCustomer(workbook, users);
            // get staff records
            importStaff(workbook, users);
            // get manager records
            importManager(workbook, users);
            // get person records and match with users
            importPerson(workbook, users);

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
            customer.setPersonId(currentRow.getCell(1) == null ? "" : currentRow.getCell(1).getStringCellValue());
            customer.setUsername(currentRow.getCell(2) == null ? "" : currentRow.getCell(2).getStringCellValue());
            customer.setPassword(currentRow.getCell(3) == null ? "" : currentRow.getCell(3).getStringCellValue());
            customer.setEmail(currentRow.getCell(4) == null ? "" : currentRow.getCell(4).getStringCellValue());
            String creditRatingStr = currentRow.getCell(5) == null ? "" : currentRow.getCell(5).getStringCellValue();
            customer.setCreditRating(customer.toCreditRating(creditRatingStr));
            users.put(customer.getUsername(), customer);
        }
    }

    public void importStaff(Workbook workbook, Map<String, Object> users) {
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
            staff.setPersonId(currentRow.getCell(1) == null ? "" : currentRow.getCell(1).getStringCellValue());
            staff.setUsername(currentRow.getCell(2) == null ? "" : currentRow.getCell(2).getStringCellValue());
            staff.setPassword(currentRow.getCell(3) == null ? "" : currentRow.getCell(3).getStringCellValue());
            staff.setEmail(currentRow.getCell(4) == null ? "" : currentRow.getCell(4).getStringCellValue());
            staff.setBasicSalary(currentRow.getCell(5) == null ? 0 : currentRow.getCell(5).getNumericCellValue());
            staff.setRateOfBonus(currentRow.getCell(6) == null ? 0 : currentRow.getCell(6).getNumericCellValue());
            users.put(staff.getUsername(), staff);
        }
    }

    public void importManager(Workbook workbook, Map<String, Object> users) {
        String sheetName = "Manager";
        Sheet datatypeSheet = workbook.getSheet(sheetName);
        Iterator<Row> iterator = datatypeSheet.iterator();
        Row firstRow = iterator.next();
        Cell firstCell = firstRow.getCell(0);
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            Manager manager = new Manager();
            manager.setUserRole(UserRole.MANAGER);
            manager.setPersonId(currentRow.getCell(0) == null ? "" : currentRow.getCell(0).getStringCellValue());
            manager.setUsername(currentRow.getCell(1) == null ? "" : currentRow.getCell(1).getStringCellValue());
            manager.setPassword(currentRow.getCell(2) == null ? "" : currentRow.getCell(2).getStringCellValue());
            manager.setEmail(currentRow.getCell(3) == null ? "" : currentRow.getCell(3).getStringCellValue());
            manager.setBasicSalary(currentRow.getCell(4) == null ? 0 : currentRow.getCell(4).getNumericCellValue());
            manager.setRateOfBonus(currentRow.getCell(5) == null ? 0 : currentRow.getCell(5).getNumericCellValue());
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
                                currentRow.getCell(1) == null ? "" : currentRow.getCell(1).getStringCellValue());
                        ((Person) user).setGender(
                                currentRow.getCell(2) == null ? "" : currentRow.getCell(2).getStringCellValue());
                        ((Person) user).setAge(
                                (int) (currentRow.getCell(3) == null ? 0
                                        : currentRow.getCell(3).getNumericCellValue()));
                        ((Person) user).setAddress(
                                currentRow.getCell(4) == null ? "" : currentRow.getCell(4).getStringCellValue());
                        // chi break neu match user
                        break;
                    }
                }
            }
        }
    }
}