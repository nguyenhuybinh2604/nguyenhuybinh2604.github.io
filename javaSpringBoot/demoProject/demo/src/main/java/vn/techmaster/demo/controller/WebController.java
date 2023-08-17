package vn.techmaster.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.techmaster.demo.model.Post;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

//@RestController = @Controller + @ResponseBody
//@Controller -> tra du lieu dang view template
//@RestController tra du lieu dang json/xml
@Controller
@RestController
@RequestMapping("/posts")
public class WebController {
    private List<Post> postList;

    public WebController() {
        System.out.println("Web controller created");

        this.postList = new ArrayList<>();
        this.postList.add(new Post(1, "Post name 1", "Author 1"));
        this.postList.add(new Post(2, "Post name 2", "Author 2"));
        this.postList.add(new Post(3, "Post name 3", "Author 3"));
    }

    // 1. Get all posts
//    @RequestMapping(method = RequestMethod.GET, value = "/posts")
    @GetMapping("")
    public List<Post> getPost() {
        return this.postList;
    }

    // 2. Get posts by id
//    @RequestMapping(method = RequestMethod.GET, value = "/posts/{id}")
    @GetMapping("/{id}")
    public Post getPostById(@PathVariable(name = "id") Integer postId) {
        return this.postList.stream()
                .filter(p -> p.getId().equals(postId))
                .findFirst().orElse(null);
    }


    // 3. Create new posts
//    @RequestMapping(method = RequestMethod.POST, value = "/posts")
    @PostMapping("")
    public Post createPost(@RequestBody Post request) {
        System.out.println(request);
        Post post = new Post();
        Random rd = new Random();
        post.setId(rd.nextInt(1000));
        post.setTitle(request.getTitle());
        post.setAuthor(request.getAuthor());

        this.postList.add(post);
        return post;
    }

    // 4. Update posts
//    @RequestMapping(method = RequestMethod.PUT, value = "/posts/{id}")
    @PutMapping("/{id}")
    public Post updatePostById(@PathVariable Integer id, @RequestBody Post request) {
        Post post = this.postList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst().orElse(null);
        if (post != null) {
            post.setTitle(request.getTitle());
            post.setAuthor(request.getAuthor());

        }
        return post;
    }

    // 5. Delete posts
//    @RequestMapping(method = RequestMethod.DELETE, value = "/posts/{id}")
    @DeleteMapping("/{id}")
    public void deletePostById(@PathVariable(name = "id") Integer postId) {
        Iterator<Post> iterator = this.postList.iterator();

        while (iterator.hasNext()) {
            Post post = iterator.next();
            if (post.getId().equals(postId)) {
                iterator.remove();
                break; // Assuming there is only one record with the matching property value
            }
        }

    }

    // 6. Search
    // /posts/search?title=bac&author=aaa
    @GetMapping("/search")
    public List<Post> searchPost(@RequestParam String title) {
        return this.postList.stream()
                .filter(p -> p.getTitle().toLowerCase().contains(title.toLowerCase()))
                .toList();
    }

}
