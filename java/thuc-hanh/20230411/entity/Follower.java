package entity;

public class Follower {
    public static int autoid;
    private int id;
    private String name;
    private String email;
    private int numberOfLikes;


    public Follower() {
    }

    public Follower(String name, String email, int numberOfLikes) {
        this.id = ++autoid;
        this.name = name;
        this.email = email;
        this.numberOfLikes = numberOfLikes;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = ++autoid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumberOfLikes() {
        return this.numberOfLikes;
    }

    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public Follower name(String name) {
        setName(name);
        return this;
    }

    public Follower email(String email) {
        setEmail(email);
        return this;
    }

    public Follower numberOfLikes(int numberOfLikes) {
        setNumberOfLikes(numberOfLikes);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", numberOfLikes='" + getNumberOfLikes() + "'" +
            "}";
    }
  
}
