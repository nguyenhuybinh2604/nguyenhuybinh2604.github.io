package entity;

public class Hero {
    private String name;
    private String position;


    public Hero() {
    }

    public Hero(String name, String position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return this.name;
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
            " " + getName() + "'" +
            ", position: '" + getPosition() + "'" +
            "}";
    }

}
