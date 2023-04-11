package entity;
import java.util.List;

public class Idol {
    public static int autoid;
    private int id;
    private String name;
    private String email;
    private String group;
    private List<Follower> followers;


    public Idol() {
    }

    public Idol(String name, String email, String group, List<Follower> followers) {
        this.id = ++autoid;
        this.name = name;
        this.email = email;
        this.group = group;
        this.followers = followers;
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

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<Follower> getFollowers() {
        return this.followers;
    }

    public void setFollowers(List<Follower> followers) {
        this.followers = followers;
    }

    public Idol name(String name) {
        setName(name);
        return this;
    }

    public Idol email(String email) {
        setEmail(email);
        return this;
    }

    public Idol group(String group) {
        setGroup(group);
        return this;
    }

    public Idol followers(List<Follower> followers) {
        setFollowers(followers);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", group='" + getGroup() + "'" +
            ", followers='" + getFollowers() + "'" +
            "}";
    }
   
}
