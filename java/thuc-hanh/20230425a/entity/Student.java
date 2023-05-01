package entity;

import java.time.LocalDate;

public class Student {
    public static int autoId;
    private int id;
    private String name;
    private LocalDate dob;
    private String hometown;

    public Student() {
        this.id = ++autoId;
    }

    public Student(String name, LocalDate dob, String hometown) {
        this.id = ++autoId;
        this.name = name;
        this.dob = dob;
        this.hometown = hometown;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return this.dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getHometown() {
        return this.hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", dob='" + getDob() + "'" +
                ", hometown='" + getHometown() + "'" +
                "} \n";
    }

}
