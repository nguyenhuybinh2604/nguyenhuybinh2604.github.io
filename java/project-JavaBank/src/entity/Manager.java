package entity;

import java.util.List;

import service.IUser;

public class Manager extends Person implements IUser {
    private UserRole userRole;
    private String username;
    private String password;
    private String email;
    private double basicSalary;
    private double rateOfBonus;
    private List<Request> requests;

    public Manager() {
    }

    @Override
    public UserRole getUserRole() {
        return this.userRole;
    }

    @Override
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    public double getBasicSalary() {
        return this.basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public double getRateOfBonus() {
        return this.rateOfBonus;
    }

    public void setRateOfBonus(double rateOfBonus) {
        this.rateOfBonus = rateOfBonus;
    }

    public List<Request> getRequests() {
        return this.requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    @Override
    public String toString() {
        return "{" +
                " username='" + getUsername() + "'" +
                " name='" + getName() + "'" +
                ", password='" + getPassword() + "'" +
                ", basicSalary='" + getBasicSalary() + "'" +
                ", bonus='" + getRateOfBonus() + "'" +
                "} \n";
    }
}
