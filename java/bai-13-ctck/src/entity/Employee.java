package entity;

public abstract class Employee {
    protected String employeeId;
    protected String name;
    protected int age;
    protected String address;
    protected double salaryBasic;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalaryBasic() {
        return salaryBasic;
    }

    public void setSalaryBasic(double salaryBasic) {
        this.salaryBasic = salaryBasic;
    }

    public double getTax() {
        return getIncome() < 11000000 ? 0 : 0.1 * getIncome();
    }

    public abstract double getIncome();

    public abstract String toString();

}
