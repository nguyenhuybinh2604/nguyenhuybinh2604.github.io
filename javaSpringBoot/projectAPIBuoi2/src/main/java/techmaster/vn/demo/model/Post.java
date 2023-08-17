package techmaster.vn.demo.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Post {

    private Integer id;


    @NotNull(message = "Tiêu đề không được để trống")
    @Size(min = 5, max = 50, message = "Tiêu đề phải từ 5 đến 50 ký tự")
    private String title;

    @NotNull(message = "Tác giả không được để trống")
    private String author;

    public Post() {
    }

    public Post(Integer id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
