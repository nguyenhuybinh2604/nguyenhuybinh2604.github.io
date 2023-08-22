package techmaster.vn.demo.dao;

import techmaster.vn.demo.model.ToDo;

import java.util.List;

public interface ToDoDAO {
    List<ToDo> findAll();

    void save(ToDo toDo);

    void delete(Integer id);

    List<ToDo> findByTitleContainsIgnoreCase(String title);
}
