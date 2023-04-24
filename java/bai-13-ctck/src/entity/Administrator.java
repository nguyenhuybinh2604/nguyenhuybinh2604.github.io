package entity;

import service.IEmployee;

public class Administrator implements IEmployee {
    private String employeeId;
    private String name;
    private int age;
    private String address;
    private double salaryBasic;

    public Administrator() {
    }

    @Override
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalaryBasic() {
        return salaryBasic;
    }

    @Override
    public void setSalaryBasic(double salaryBasic) {
        this.salaryBasic = salaryBasic;
    }

    @Override
    public void setSalaryRole(double salaryRole) {

    }

    @Override
    public void setSales(int sales) {

    }

    @Override
    public void setRateOfBonus(double rateOfBonus) {

    }

    @Override
    public double getIncome() {
        return salaryBasic;
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
                "Basic Salary: " + salaryBasic + " " +
                "Income: " + getIncome() + " " +
                "Tax: " + getTax() + " " +
                "} \n";
    }

}
