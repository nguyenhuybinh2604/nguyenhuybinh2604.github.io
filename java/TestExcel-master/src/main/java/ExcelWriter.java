import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

public class ExcelWriter {
    public void writeExcel(List<Customer> customers) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("CustomerSheet");
        int rowCount =0;
        Row firstRow = sheet.createRow(rowCount++);
        Cell firstCell = firstRow.createCell(0);
        firstCell.setCellValue("Stt");
        firstRow.createCell(1).setCellValue("Name");
        firstRow.createCell(2).setCellValue("Email");

        for (Customer customer: customers){
            Row row = sheet.createRow(rowCount++);
            Cell cell1 = row.createCell(0);
            cell1.setCellValue(customer.getId());
            Cell cell2 = row.createCell(1);
            cell2.setCellValue(customer.getName());
            Cell cell3 = row.createCell(2);
            cell3.setCellValue(customer.getEmail());
        }
        FileOutputStream fos = new FileOutputStream("TestExcel.xlsx");
        workbook.write(fos);
        workbook.close();
    }

    public List<Customer> readExcel() throws IOException {
        FileInputStream excel = new FileInputStream(new File("TestExcel.xlsx"));
        Workbook workbook = new XSSFWorkbook(excel);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = sheet.iterator();
        Row firstRow = iterator.next();
       // Cell firstCell = firstRow.getCell(0);
        ArrayList<Customer> customers = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();
        while (iterator.hasNext()){
            Row currentRow = iterator.next();
            Customer customer = new Customer(Integer.parseInt(formatter.formatCellValue(currentRow.getCell(0))),
                    currentRow.getCell(1).getStringCellValue(),currentRow.getCell(2).getStringCellValue());
            customers.add(customer);
        }
        return customers;
    }

}
