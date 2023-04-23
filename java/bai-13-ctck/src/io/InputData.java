package io;

import entity.Administrator;
import entity.Manager;
import entity.Marketer;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class InputData {
    public void readExcel(List<Administrator> administrators, List<Manager> managers, List<Marketer> marketers) {
        try {
            String pathProject = System.getProperty("user.dir");
            FileInputStream excelFile = new FileInputStream(new File(pathProject + "/src/io/data.xlsx"));
            Workbook workbook = new XSSFWorkbook(excelFile);
            //DataFormatter fmt = new DataFormatter();
            importAdministrator(workbook, administrators);
            importManager(workbook, managers);
            importMarketer(workbook, marketers);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("JavaBank Error: file not found.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("JavaBank Error: IO error.");
        }
    }

    private void importAdministrator(Workbook workbook, List<Administrator> administrators) {
        String sheetName = "Administrator";
        Sheet datatypeSheet = workbook.getSheet(sheetName);
        Iterator<Row> iterator = datatypeSheet.iterator();
        Row firstRow = iterator.next();
        Cell firstCell = firstRow.getCell(0);
        System.out.println(firstCell.getStringCellValue());
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            Administrator administrator = new Administrator();
            administrator.setEmployeeId(currentRow.getCell(0) == null ? "" : currentRow.getCell(0).getStringCellValue());
            administrator.setName(currentRow.getCell(1) == null ? "" : currentRow.getCell(1).getStringCellValue());
            administrator.setAge((int) (currentRow.getCell(2) == null ? 0 : currentRow.getCell(2).getNumericCellValue()));
            administrator.setAddress(currentRow.getCell(3) == null ? "" : currentRow.getCell(3).getStringCellValue());
            administrator.setSalaryBasic(currentRow.getCell(4) == null ? 0 : currentRow.getCell(4).getNumericCellValue());
            administrators.add(administrator);
        }
        for (Administrator administrator : administrators) {
            System.out.println(administrator);
        }
    }

    public void importManager(Workbook workbook, List<Manager> managers) {
        String sheetName = "Manager";
        Sheet datatypeSheet = workbook.getSheet(sheetName);
        Iterator<Row> iterator = datatypeSheet.iterator();
        Row firstRow = iterator.next();
        Cell firstCell = firstRow.getCell(0);
        System.out.println(firstCell.getStringCellValue());
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            Manager manager = new Manager();
            manager.setEmployeeId(currentRow.getCell(0) == null ? "" : currentRow.getCell(0).getStringCellValue());
            manager.setName(currentRow.getCell(1) == null ? "" : currentRow.getCell(1).getStringCellValue());
            manager.setAge((int) (currentRow.getCell(2) == null ? 0 : currentRow.getCell(2).getNumericCellValue()));
            manager.setAddress(currentRow.getCell(3) == null ? "" : currentRow.getCell(3).getStringCellValue());
            manager.setSalaryBasic(currentRow.getCell(4) == null ? 0 : currentRow.getCell(4).getNumericCellValue());
            manager.setSalaryRole(currentRow.getCell(5) == null ? 0 : currentRow.getCell(5).getNumericCellValue());
            managers.add(manager);
        }
        for (Manager manager : managers) {
            System.out.println(manager);
        }
    }

    public void importMarketer(Workbook workbook, List<Marketer> marketers) {
        String sheetName = "Marketer";
        Sheet datatypeSheet = workbook.getSheet(sheetName);
        Iterator<Row> iterator = datatypeSheet.iterator();
        Row firstRow = iterator.next();
        Cell firstCell = firstRow.getCell(0);
        System.out.println(firstCell.getStringCellValue());
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            Marketer marketer = new Marketer();
            marketer.setEmployeeId(currentRow.getCell(0) == null ? "" : currentRow.getCell(0).getStringCellValue());
            marketer.setName(currentRow.getCell(1) == null ? "" : currentRow.getCell(1).getStringCellValue());
            marketer.setAge((int) (currentRow.getCell(2) == null ? 0 : currentRow.getCell(2).getNumericCellValue()));
            marketer.setAddress(currentRow.getCell(3) == null ? "" : currentRow.getCell(3).getStringCellValue());
            marketer.setSalaryBasic(currentRow.getCell(4) == null ? 0 : currentRow.getCell(4).getNumericCellValue());
            marketer.setSales((int) (currentRow.getCell(5) == null ? 0 : currentRow.getCell(5).getNumericCellValue()));
            marketer.setRateOfBonus(currentRow.getCell(6) == null ? 0 : currentRow.getCell(6).getNumericCellValue());
            marketers.add(marketer);
        }
        for (Marketer marketer : marketers) {
            System.out.println(marketer);
        }
    }
}