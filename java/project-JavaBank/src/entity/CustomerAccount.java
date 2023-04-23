package entity;

import java.util.Date;

public class CustomerAccount {
    private String id;
    private String customerId;
    private Date openDate;
    private String currency;   
    private double balance;

    public CustomerAccount() {
    }

    public CustomerAccount(String id, String customerId, Date openDate, String currency, double balance) {
        this.id = id;
        this.customerId = customerId;
        this.openDate = openDate;
        this.currency = currency;
        this.balance = balance;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getOpenDate() {
        return this.openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", customerId='" + getCustomerId() + "'" +
            ", openDate='" + getOpenDate() + "'" +
            ", currency='" + getCurrency() + "'" +
            ", amount='" + getBalance() + "'" +
            "}";
    }

}
