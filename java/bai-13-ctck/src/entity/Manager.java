package entity;

import service.IEmployee;

public class Manager implements IEmployee {
    private String employeeId;
    private String name;
    private int age;
    private String address;
    private double salaryBasic;
    private double salaryRole;

    public Manager() {
    }

    @Override
    public String getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return this.address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalaryBasic() {
        return this.salaryBasic;
    }

    @Override
    public void setSalaryBasic(double salaryBasic) {
        this.salaryBasic = salaryBasic;
    }

    @Override
    public void setSales(int sales) {

    }

    @Override
    public void setRateOfBonus(double rateOfBonus) {

    }

    public double getSalaryRole() {
        return salaryRole;
    }

    @Override
    public void setSalaryRole(double salaryRole) {
        this.salaryRole = salaryRole;
    }

    @Override
    public double getIncome() {
        return salaryRole + salaryBasic;
    }

    @Override
    public double getTax() {
        return getIncome() < 11000000 ? 0 : 0.1 * getIncome();
    }

    @Override
    public String toString() {
        return "{" +
                "Id: " + employeeId + ", " +
                "Name: " + name + ", " +
                "Age: " + age + ", " +
                "Address: " + address + ", " +
                "Basic Salary: " + salaryBasic + ", " +
                "Role Salary: " + salaryRole + " " +
                "Income: " + getIncome() + " " +
                "Tax: " + getTax() + " " +
                "} \n";
    }

}