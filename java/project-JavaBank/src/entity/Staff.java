package entity;

import java.util.List;

import service.IUser;

public class Staff extends Person implements IUser {
    private int staffId;
    private UserRole userRole;
    private String username;
    private String password;
    private String email;
    private double basicSalary;
    private double rateOfBonus;
    private UserStatus userStatus;
    private List<Message> requests;

    public Staff() {
    }

    public Staff(int staffId, String personId, String username, String password, String email,
                 String name, String gender, int age, String address, UserStatus userStatus) {
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

    public List<Message> getRequests() {
        return this.requests;
    }

    public void setRequests(List<Message> requests) {
        this.requests = requests;
    }

    @Override
    public String toString() {
        return "{" +
                " staffId='" + getStaffId() + "'" +
                " staffName='" + getName() + "'" +
                ", username='" + getUsername() + "'" +
                ", password='" + getPassword() + "'" +
                ", basicSalary='" + getBasicSalary() + "'" +
                ", bonus='" + getRateOfBonus() + "'" +
                "} \n";
    }

}