package entity;

public class Manager extends Person {

    public Manager() {
    }

    public Manager(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {

        return 
                " Ten: " + this.name + " " +
                ", Tuoi: " + this.age + " \n";

    }

}
