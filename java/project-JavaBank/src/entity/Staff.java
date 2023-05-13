package entity;

import java.util.ArrayList;
import java.util.List;

import service.IUser;

public class Staff extends Person implements IUser {
    private int staffId;
    private UserRole userRole;
    private String username;
    private String password;
    private String email;
    private double basicSalary;
    private Integer rank;
    private double bonus;
    private UserStatus userStatus;

    public Staff() {
    }

    public Staff(int staffId, String personId, String username, String password, String email, String name, String gender,
                 int age, String address, UserStatus userStatus) {
        this.staffId = staffId;
        this.userRole = UserRole.STAFF;
        this.personId = personId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.userStatus = userStatus;
        this.rank = 0;
        this.bonus = 0;
    }

    public int getStaffId() {
        return this.staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
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

    public int getRank() {
        return this.rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
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
                " staffId='" + getStaffId() + "'" +
                " staffName='" + getName() + "'" +
                ", username='" + getUsername() + "'" +
                ", password='" + getPassword() + "'" +
                ", basicSalary='" + getBasicSalary() + "'" +
                ", rank='" + getRank() + "'" +
                ", bonus='" + getBonus() + "'" +
                "} \n";
    }

}
