package techmaster.vn.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import techmaster.vn.demo.dao.ToDoDAO;
import techmaster.vn.demo.exception.BadRequestException;
import techmaster.vn.demo.model.ToDo;

import java.util.List;
import java.util.Random;

@Service
public class ToDoServiceImpl implements ToDoService {

    @Autowired
    private ToDoDAO toDoDAO;

    @Override
    public List<ToDo> getAllToDo() {
        return toDoDAO.findAll();
    }

    @Override
    public ToDo getToDoById(Integer id) {
        List<ToDo> toDoList = toDoDAO.findAll();
        return toDoList.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found to do");
                });
    }

    @Override
    public ToDo createToDo(ToDo request) {
        ToDo toDo = new ToDo();
        Random rd = new Random();
        toDo.setId(rd.nextInt(1000));
        toDo.setTitle(request.getTitle());
        toDo.setStatus(request.getStatus());

        toDoDAO.save(toDo);
        return toDo;
    }

    @Override
    public ToDo updateToDo(Integer id, ToDo request) {
        ToDo toDo = getToDoById(id);
        toDo.setTitle(request.getTitle());
        toDo.setStatus(request.getStatus());
        return toDo;
    }

    @Override
    public void deleteToDo(Integer id) {
        ToDo toDo = getToDoById(id);
        toDoDAO.delete(id);
    }

    @Override
    public List<ToDo> searchToDo(String title) {
        if (title.trim().isEmpty())
            throw new BadRequestException("Title không được để trống");
        return toDoDAO.findByTitleContainsIgnoreCase(title);
    }

}
