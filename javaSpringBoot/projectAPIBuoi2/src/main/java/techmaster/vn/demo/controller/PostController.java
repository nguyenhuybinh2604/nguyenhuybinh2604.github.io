package techmaster.vn.demo.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import techmaster.vn.demo.dao.PostDAO;
import techmaster.vn.demo.model.Post;
import techmaster.vn.demo.response.ErrorResponse;
import techmaster.vn.demo.service.PostService;

import java.util.*;

@CrossOrigin // Danh dau public len class
@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // 1. Lấy ds tất cả post
//    @RequestMapping(method = RequestMethod.GET, value = "/posts")
    @GetMapping("")
    public ResponseEntity<?> getAllPost() {
        return ResponseEntity.ok(postService.getAllPost()); // status: 200; body = List<Post>;
    }

    // 2. Lấy post theo id
    // /posts/1 , /posts/2
//    @RequestMapping(method = RequestMethod.GET, value = "/posts/{id}")
    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(postService.getPostById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // 3. Tao moi
    @PostMapping("")
    public ResponseEntity<?> createPost(@Valid @RequestBody Post request) {
        return new ResponseEntity<>(postService.createPost(request), HttpStatus.CREATED); // Status 201
    }


    // 4. Update posts
//    @RequestMapping(method = RequestMethod.PUT, value = "/posts/{id}")
    @PutMapping("{id}")
    public ResponseEntity<?> updatePost(@PathVariable Integer id, @Valid @RequestBody Post request) {
        return ResponseEntity.ok().body(postService.updatePost(id, request));
    }

    // 5. Delete posts
//    @RequestMapping(method = RequestMethod.DELETE, value = "/posts/{id}")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePostById(@PathVariable(name = "id") Integer postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

    // 6. Search
    // /posts/search?title=bac&author=aaa
    @GetMapping("/search")
    public ResponseEntity<?> searchPost(@RequestParam String title) {
        return ResponseEntity.ok(postService.searchPost(title));
    }

}
