package techmaster.vn.demo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import techmaster.vn.demo.model.ToDo;
import techmaster.vn.demo.service.ToDoService;

@CrossOrigin // Danh dau public len class
@RestController
@RequestMapping("/todos")
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    // 1. Lấy ds tất cả post
//    @RequestMapping(method = RequestMethod.GET, value = "/posts")
    @GetMapping("")
    public ResponseEntity<?> getAllToDo() {
        return ResponseEntity.ok(toDoService.getAllToDo()); // status: 200; body = List<Post>;
    }

    // 2. Lấy post theo id
    // /posts/1 , /posts/2
//    @RequestMapping(method = RequestMethod.GET, value = "/posts/{id}")
    @GetMapping("/{id}")
    public ResponseEntity<?> getToDoById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(toDoService.getToDoById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // 3. Tao moi
    @PostMapping("")
    public ResponseEntity<?> createToDo(@Valid @RequestBody ToDo request) {
        return new ResponseEntity<>(toDoService.createToDo(request), HttpStatus.CREATED); // Status 201
    }


    // 4. Update posts
//    @RequestMapping(method = RequestMethod.PUT, value = "/posts/{id}")
    @PutMapping("{id}")
    public ResponseEntity<?> updateToDo(@PathVariable Integer id, @Valid @RequestBody ToDo request) {
        return ResponseEntity.ok().body(toDoService.updateToDo(id, request));
    }

    // 5. Delete posts
//    @RequestMapping(method = RequestMethod.DELETE, value = "/posts/{id}")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteToDoById(@PathVariable(name = "id") Integer toDoId) {
        toDoService.deleteToDo(toDoId);
        return ResponseEntity.noContent().build();
    }

    // 6. Search
    // /posts/search?title=bac&author=aaa
    @GetMapping("/search")
    public ResponseEntity<?> searchToDo(@RequestParam String title) {
        return ResponseEntity.ok(toDoService.searchToDo(title));
    }

}
