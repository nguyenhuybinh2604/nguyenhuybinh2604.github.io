package service;

public interface IAccount {
    void recharge(double amount);
    void changeEmail(String email);
    void displayInfo();
}