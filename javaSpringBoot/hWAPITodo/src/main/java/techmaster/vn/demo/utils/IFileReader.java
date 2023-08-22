package techmaster.vn.demo.utils;

import techmaster.vn.demo.model.ToDo;

import java.util.List;

public interface IFileReader {
    List<ToDo> readFile(String filePath);
}
