package entity;

import service.IUser;

public class Manager extends Person implements IUser {
    private UserRole userRole;
    private UserStatus userStatus;
    private String username;
    private String password;
    private String email;
    private double basicSalary;
    private double bonus;

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

    @Override
    public UserStatus getUserStatus() {
        return this.userStatus;
    }

    @Override
    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public void setUserStatus(String userStatusStr) {
        if (userStatusStr.equalsIgnoreCase("ACTIVE")) this.userStatus = UserStatus.ACTIVE;
        else if (userStatusStr.equalsIgnoreCase("INACTIVE")) this.userStatus = UserStatus.INACTIVE;
        else if (userStatusStr.equalsIgnoreCase("LOCKED")) this.userStatus = UserStatus.LOCKED;
        else this.userStatus = UserStatus.NA;
    }

    public double getBasicSalary() {
        return this.basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public double getBonus() {
        return this.bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return "{" +
                " username='" + getUsername() + "'" +
                " name='" + getName() + "'" +
                ", password='" + getPassword() + "'" +
                ", basicSalary='" + getBasicSalary() + "'" +
                ", bonus='" + getBonus() + "'" +
                "} \n";
    }
}
