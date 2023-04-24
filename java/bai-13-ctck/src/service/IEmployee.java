package service;

public interface IEmployee {
    public String getEmployeeId();

    public String getName();

    public void setName(String name);

    public void setAge(int age);

    public void setAddress(String address);

    public void setSalaryBasic(double salaryBasic);

    public void setSalaryRole(double salaryRole);

    public void setSales(int sales);

    public void setRateOfBonus(double rateOfBonus);

    public double getIncome();

    public double getTax();

    public String toString();
}
