package entity;

import java.time.LocalDate;

public class Worker {
    public static int autoid;
    private int id;
    private String name;
    private int age;
    private double salary;
    private String workplace;
    private String salaryStatus;
    private LocalDate salaryDate;

    public Worker() {
        this.id = ++autoid;
    }

    public Worker(String name, int age, double salary, String workplace) {
        this.id = ++autoid;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.workplace = workplace;
        this.salaryDate = LocalDate.now();
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

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getWorkplace() {
        return this.workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getSalaryStatus() {
        return this.salaryStatus;
    }

    public void setSalaryStatus(String salaryStatus) {
        this.salaryStatus = salaryStatus;
    }

    public LocalDate getSalaryDate() {
        return this.salaryDate;
    }

    public void setSalaryDate(LocalDate salaryDate) {
        this.salaryDate = salaryDate;
    }

    @Override
    public String toString() {
        return " No." + getId() + " \t"
                + getName() + " \t"
                + getAge() + " \t"
                + getSalary() + " \t"
                + getSalaryStatus() + " \t"
                + getSalaryDate() + " \n";
    }

}
