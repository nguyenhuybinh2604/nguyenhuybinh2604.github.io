package entity;

import service.IClassification;

public class Student implements IClassification {
    private String name;
    private int age;
    private double marks;
    private String classification;

    public Student() {
    }

    public Student(String name, int age, double marks, String classification) {
        this.name = name;
        this.age = age;
        this.marks = marks;
        this.classification = classification;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getMarks() {
        return this.marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "{" +
                " name='" + getName() + "'" +
                ", age='" + getAge() + "'" +
                ", marks='" + getMarks() + "'" +
                ", classification='" + classify() + "'" +
                "}";
    }

    @Override
    public void display() {
        System.out.println(toString());
    }

    @Override
    public String classify() {
        if (this.marks >= 8) {
            this.classification = "A";
            return "A";
        } else if (this.marks < 8 && this.marks > 6.5) {
            this.classification = "B";
            return "B";
        } else {
            this.classification = "C";
            return "C";
        }
    }
}
