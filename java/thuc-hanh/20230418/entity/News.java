package entity;

import service.INews;

public class News implements INews {
    private String title;
    private String author;
    private String publicDate;
    double rate;
    

    public News() {
    }

    public News(String title, String author, String publicDate, double rate) {
        this.title = title;
        this.author = author;
        this.publicDate = publicDate;
        this.rate = rate;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicDate() {
        return this.publicDate;
    }

    public void setPublicDate(String publicDate) {
        this.publicDate = publicDate;
    }

    public double getRate() {
        return this.rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public News title(String title) {
        setTitle(title);
        return this;
    }

    public News author(String author) {
        setAuthor(author);
        return this;
    }

    public News publicDate(String publicDate) {
        setPublicDate(publicDate);
        return this;
    }

    public News rate(double rate) {
        setRate(rate);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " title='" + getTitle() + "'" +
            ", author='" + getAuthor() + "'" +
            ", publicDate='" + getPublicDate() + "'" +
            ", rate='" + getRate() + "'" +
            "}";
    }
    
@Override
public
String display() {
    return this.toString();
}
}
