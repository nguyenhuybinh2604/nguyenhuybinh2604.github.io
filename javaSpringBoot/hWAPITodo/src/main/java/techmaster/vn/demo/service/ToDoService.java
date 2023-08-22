package techmaster.vn.demo.service;

import techmaster.vn.demo.model.ToDo;

import java.util.List;

public interface ToDoService {
    List<ToDo> getAllToDo();

    ToDo getToDoById(Integer id);

    ToDo createToDo(ToDo request);

    ToDo updateToDo(Integer id, ToDo request);

    void deleteToDo(Integer id);

    List<ToDo> searchToDo(String title);
}
