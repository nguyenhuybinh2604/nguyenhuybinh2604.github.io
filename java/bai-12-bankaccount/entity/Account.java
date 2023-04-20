package entity;

import service.IAccount;

public class Account implements IAccount {
    private String name;
    private String accountNumber;
    private String email;
    private double balance;

    public Account() {
    }

    public Account(String name, String accountNumber, String email, double balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.email = email;
        this.balance = balance;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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
                " name='" + getName() + "'" +
                ", accountNumber='" + getAccountNumber() + "'" +
                ", email='" + getEmail() + "'" +
                ", balance='" + getBalance() + "'" +
                "} \n";
    }

    @Override
    public void displayInfo() {
        System.out.println("Thong tin tai khoan:");
        System.out.println(toString()); 
    }

    @Override
    public void recharge(double amount) {
        this.balance = this.balance + amount;
    }

    @Override
    public void changeEmail(String email) {
        this.email = email;
    }

}
