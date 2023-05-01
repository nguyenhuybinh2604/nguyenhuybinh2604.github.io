package entity;

import java.time.LocalDate;

public abstract class Product {


    public Product() {
    }

    public Product(LocalDate valueDate, LocalDate maturityDate, String currency, String customerId,
            String staffId, double interestRate, double balance, double convertedBalance) {
    
        this.valueDate = valueDate;
        this.maturityDate = maturityDate;
        this.currency = currency;
        this.customerId = customerId;
        this.staffId = staffId;
        this.interestRate = interestRate;
        this.balance = balance;
        this.convertedBalance = convertedBalance;
    }

    public abstract String getProductId();

    public abstract void setProductId(String productId);

    public LocalDate getValueDate() {
        return this.valueDate;
    }

    public void setValueDate(LocalDate valueDate) {
        this.valueDate = valueDate;
    }

    public LocalDate getMaturityDate() {
        return this.maturityDate;
    }

    public void setMaturityDate(LocalDate maturityDate) {
        this.maturityDate = maturityDate;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getStaffId() {
        return this.staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public double getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getConvertedBalance() {
        return this.convertedBalance;
    }

    public void setConvertedBalance(double convertedBalance) {
        this.convertedBalance = convertedBalance;
    }

    @Override
    public String toString() {
        return "{" +
                " productId='" + getProductId() + "'" +
                ", valueDate='" + getValueDate() + "'" +
                ", maturityDate='" + getMaturityDate() + "'" +
                ", currency='" + getCurrency() + "'" +
                ", customerId='" + getCustomerId() + "'" +
                ", interestRate='" + getInterestRate() + "'" +
                ", balance='" + getBalance() + "'" +
                ", convertedBalance='" + getConvertedBalance() + "'" +
                "}";
    }

}
