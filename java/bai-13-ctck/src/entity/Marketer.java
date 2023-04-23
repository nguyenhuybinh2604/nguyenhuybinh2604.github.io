package entity;

public class Marketer extends Employee{
    private int sales;
    private double rateOfBonus;

    public Marketer() {
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public double getRateOfBonus() {
        return rateOfBonus;
    }

    public void setRateOfBonus(double rateOfBonus) {
        this.rateOfBonus = rateOfBonus;
    }

    public double getSalaryBonus() {
        return sales*rateOfBonus;
    }

    @Override
    public double getIncome() {
        return getSalaryBonus()+salaryBasic;
    }
    @Override
    public String toString(){
        return "{" +
                "Id: " + employeeId + ", " +
                "Name: " + name + ", " +
                "Age: " + age + ", " +
                "Address: " + address + ", " +
                "Basic Salary: " + salaryBasic + ", " +
                "Sales: " + sales + ", " +
                "Rate of bonus: " + rateOfBonus + ", " +
                "Income: " + getIncome() + " " +
                "Tax: " + getTax() + " " +
                "}";
    }
}
