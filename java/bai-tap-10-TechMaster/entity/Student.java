package entity;

public class Student extends Person{
    public static int autoId;
    private int id;
    private String rank;


    public Student() {
        this.id = ++autoId;
    }

    public Student(String name, int age, String rank) {
        this.id = ++autoId;
        this.name = name;
        this.age = age;       
        this.rank = rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "{" +
            " Id: " + this.id + " " +
            ", Ten: " + this.name + " " +
            ", Tuoi: " + this.age + " " +
            ", Hoc luc: " + this.rank + " \n";
    }

}
