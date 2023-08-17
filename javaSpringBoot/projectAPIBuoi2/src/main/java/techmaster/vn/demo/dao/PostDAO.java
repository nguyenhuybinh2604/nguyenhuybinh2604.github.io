package techmaster.vn.demo.dao;

import org.springframework.stereotype.Repository;
import techmaster.vn.demo.database.PostDB;
import techmaster.vn.demo.model.Post;

import java.util.List;

@Repository
public class PostDAO {

    public List<Post> findAll() {
        return PostDB.postList;
    }

    public void save(Post post) {
        PostDB.postList.add(post);
    }

    public void delete(Integer id) {
        PostDB.postList.removeIf(post -> post.getId().equals(id));
    }

    public List<Post> findByTitleContainsIgnoreCase(String title) {
        return PostDB.postList.stream()
                .filter(post -> post.getTitle().toLowerCase().contains(title.toLowerCase()))
                .toList();
    }

}
