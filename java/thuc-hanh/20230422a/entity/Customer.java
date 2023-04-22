package entity;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    public static int autoId;
    private int id;
    private String name;
    private String address;
    private String counterId;
    private double payableAmount;
    public List<AdminEmployee> bills;

    public Customer() {
        this.id = ++autoId;
        this.bills = new ArrayList<>();
    }

    public Customer(String name, String address, String counterId) {
        this.id = ++autoId;
        this.name = name;
        this.address = address;
        this.counterId = counterId;
        this.bills = new ArrayList<>();
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getBill() {
        return this.bills.toString();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCounterId() {
        return this.counterId;
    }

    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }

    public double getPayableAmount() {       
        return this.payableAmount;
    }

    public void setPayableAmount() {
        double returnValue = 0;
        for (AdminEmployee bill : this.bills) {
            returnValue = returnValue + bill.getPayableAmount();
        }
        this.payableAmount = returnValue;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", address='" + getAddress() + "'" +
                ", counterId='" + getCounterId() + "'" +
                "} \n";
    }

}
