package entity;

public class Bill {
    public static int autoId;
    private int id;
    private double previousNumber;
    private double currentNumber;

    public Bill() {
        this.id = ++autoId;
    }

    public Bill(double previousNumber, double currentNumber) {
        this.id = ++autoId;
        this.previousNumber = previousNumber;
        this.currentNumber = currentNumber;
    }

    public int getId() {
        return this.id;
    }

    public double getPreviousNumber() {
        return this.previousNumber;
    }

    public void setPreviousNumber(double previousNumber) {
        this.previousNumber = previousNumber;
    }

    public double getCurrentNumber() {
        return this.currentNumber;
    }

    public void setCurrentNumber(double currentNumber) {
        this.currentNumber = currentNumber;
    }

    public double getPayableAmount() {
        return (this.currentNumber - this.previousNumber) * 750;
    }

    @Override
    public String toString() {
        return "{" +
                ", previousNumber='" + getPreviousNumber() + "'" +
                ", currentNumber='" + getCurrentNumber() + "'" +
                ", payableAmount='" + getPayableAmount() + "'" +
                "}";
    }

}
