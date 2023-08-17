package techmaster.vn.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import techmaster.vn.demo.dao.PostDAO;
import techmaster.vn.demo.exception.BadRequestException;
import techmaster.vn.demo.model.Post;

import java.util.List;
import java.util.Random;

@Service
public class PostService {

    @Autowired
    private PostDAO postDAO;

    public List<Post> getAllPost() {
        return postDAO.findAll();
    }

    public Post getPostById(Integer id) {
        List<Post> postList = postDAO.findAll();
        return postList.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found post");
                });
    }

    public Post createPost(Post request) {
        Post post = new Post();
        Random rd = new Random();
        post.setId(rd.nextInt(1000));
        post.setTitle(request.getTitle());
        post.setAuthor(request.getAuthor());

        postDAO.save(post);
        return post;
    }

    public Post updatePost(Integer id, Post request) {
        Post post = getPostById(id);
        post.setTitle(request.getTitle());
        post.setAuthor(request.getAuthor());
        return post;
    }

    public void deletePost(Integer id) {
        Post post = getPostById(id);
        postDAO.delete(id);
    }

    public List<Post> searchPost(String title) {
        if (title.trim().isEmpty())
            throw new BadRequestException("Title không được để trống");
        return postDAO.findByTitleContainsIgnoreCase(title);
    }

}
