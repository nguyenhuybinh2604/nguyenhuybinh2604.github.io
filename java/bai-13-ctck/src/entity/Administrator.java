package entity;

public class Administrator extends Employee{

    public Administrator() {
    }
    @Override
    public double getIncome() {
        return salaryBasic;
    }
    @Override
    public String toString(){
        return "{" +
                "Id: " + employeeId + ", " +
                "Name: " + name + ", " +
                "Age: " + age + ", " +
                "Address: " + address + ", " +
                "Basic Salary: " + salaryBasic + " " +
                "Income: " + getIncome() + " " +
                "Tax: " + getTax() + " " +
                "}";
    }
}
