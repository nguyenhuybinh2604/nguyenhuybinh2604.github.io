package entity;

public class Hero {
    public static int autoid;
    private int id;
    private String name;
    private String position;

    public Hero() {
        this.id = ++autoid;
    }

    public Hero(String name, String position) {
        this.id = ++autoid;
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return this.name;
    }
    
    public int getId() {
        return this.id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Hero name(String name) {
        setName(name);
        return this;
    }

    public Hero position(String position) {
        setPosition(position);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " name='" + getName() + "'" +
                ", position='" + getPosition() + "'" +
                "}";
    }

}
