package techmaster.vn.demo.dao;

import org.springframework.stereotype.Repository;
import techmaster.vn.demo.database.ToDoDB;
import techmaster.vn.demo.model.ToDo;

import java.util.List;

@Repository
public class ToDoDAOImpl implements ToDoDAO {

    @Override
    public List<ToDo> findAll() {
        return ToDoDB.toDoList;
    }

    @Override
    public void save(ToDo toDo) {
        ToDoDB.toDoList.add(toDo);
    }

    @Override
    public void delete(Integer id) {
        ToDoDB.toDoList.removeIf(toDo -> toDo.getId().equals(id));
    }

    @Override
    public List<ToDo> findByTitleContainsIgnoreCase(String title) {
        return ToDoDB.toDoList.stream()
                .filter(toDo -> toDo.getTitle().toLowerCase().contains(title.toLowerCase()))
                .toList();
    }

}
