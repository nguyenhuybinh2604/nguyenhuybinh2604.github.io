import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        ExcelWriter excelWriter = new ExcelWriter();
        List<Customer> listOfCustomer = new ArrayList<Customer>();
        listOfCustomer.add(new Customer(1, "Sylvester Stallone", "abc@gmail.com"));
        listOfCustomer.add(new Customer(2, "Tom Cruise", "xyz@yahoo.com"));
        listOfCustomer.add(new Customer(3, "Vin Diesel", "abc@hotmail.com"));
        excelWriter.writeExcel(listOfCustomer);

    }
}
