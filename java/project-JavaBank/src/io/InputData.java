package io;

import entity.UserAccount;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InputData {
    public void readExcel(String sheetName) {
        try {
            String pathProject = System.getProperty("user.dir");
            FileInputStream excelFile = new FileInputStream(new File(pathProject+"/src/io/data.xlsx"));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheet(sheetName);
            DataFormatter fmt = new DataFormatter();
            Iterator<Row> iterator = datatypeSheet.iterator();
            Row firstRow = iterator.next();
            Cell firstCell = firstRow.getCell(0);
            System.out.println(firstCell.getStringCellValue());
            List<UserAccount> userAccounts = new ArrayList<>();
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                UserAccount userAccount = new UserAccount();
                userAccount.setUsername(currentRow.getCell(0).getStringCellValue());
                userAccount.setEmail(currentRow.getCell(1).getStringCellValue());
                userAccount.setPassword(currentRow.getCell(2).getStringCellValue());
                userAccount.setRole(currentRow.getCell(3).getStringCellValue());
                userAccounts.add(userAccount);
            }
            for (UserAccount userAccount : userAccounts) {
                System.out.println(userAccount);
            }
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("JavaBank Error: file not found.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("JavaBank Error: IO error.");
        }
    }
}