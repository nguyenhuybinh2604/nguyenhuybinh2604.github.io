package techmaster.vn.demo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import techmaster.vn.demo.model.ToDo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class ExcelReader implements IFileReader {
    @Override
    public List<ToDo> readFile(String filePath) {
        List<ToDo> dataList = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(file);

            Sheet sheet = workbook.getSheetAt(0);

            int i = 0;

            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            for (Row row : sheet) {
                ToDo toDo = new ToDo();
                for (Cell cell : row) {

                    System.out.println(cell.getStringCellValue());

                    switch (cell.getCellType()) {
                        case NUMERIC: {
                            toDo.setId(Integer.parseInt(cell.getNumericCellValue() + ""));
                            break;
                        }
                        case STRING: {
                            toDo.setTitle(cell.getRichStringCellValue().getString());
                            break;
                        }
                        case BOOLEAN: {
                            toDo.setStatus(Boolean.parseBoolean(cell.getBooleanCellValue() + ""));
                            break;
                        }
                        default:
                    }
                }
                dataList.add(toDo);
                i++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return dataList;
    }
}
