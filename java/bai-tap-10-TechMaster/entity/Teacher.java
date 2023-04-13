package entity;

public class Teacher extends Person {

    public Teacher() {
    }

    public Teacher(String name, int age) {
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
