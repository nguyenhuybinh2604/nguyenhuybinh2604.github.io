package techmaster.vn.demo.dao;

import org.springframework.stereotype.Repository;
import techmaster.vn.demo.database.PostDB;
import techmaster.vn.demo.model.Post;

import java.util.List;

@Repository
public class PostDAOImpl implements PostDAO {

    @Override
    public List<Post> findAll() {
        return PostDB.postList;
    }

    @Override
    public void save(Post post) {
        PostDB.postList.add(post);
    }

    @Override
    public void delete(Integer id) {
        PostDB.postList.removeIf(post -> post.getId().equals(id));
    }

    @Override
    public List<Post> findByTitleContainsIgnoreCase(String title) {
        return PostDB.postList.stream()
                .filter(post -> post.getTitle().toLowerCase().contains(title.toLowerCase()))
                .toList();
    }

}
