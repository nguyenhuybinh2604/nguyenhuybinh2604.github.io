package vn.techmaster.demo.model;

import lombok.*;

//@Getter;
//@Setter;
//@NoArgsConstructor;
//@AllArgsConstructor;
//@Builder; // design pattern builder;
public class Post {
    private Integer id;
    private String title;
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
