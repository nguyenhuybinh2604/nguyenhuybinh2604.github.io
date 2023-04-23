package entity;

public class Manager extends Employee{
private double salaryRole;

    public Manager() {
    }

    public double getSalaryRole() {
        return salaryRole;
    }

    public void setSalaryRole(double salaryRole) {
        this.salaryRole = salaryRole;
    }

    @Override
    public double getIncome() {
        return salaryRole+salaryBasic;
    }

    @Override
    public String toString(){
        return "{" +
                "Id: " + employeeId + ", " +
                "Name: " + name + ", " +
                "Age: " + age + ", " +
                "Address: " + address + ", " +
                "Basic Salary: " + salaryBasic + ", " +
                "Role Salary: " + salaryRole + " " +
                "Income: " + getIncome() + " " +
                "Tax: " + getTax() + " " +
                "}";
    }

}