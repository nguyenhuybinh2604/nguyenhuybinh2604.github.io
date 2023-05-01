package entity;

import java.util.List;

import service.IUser;

public class Customer extends Person implements IUser {
    private int customerId;
    private UserRole userRole;
    private String username;
    private String password;
    private String email;
    private CreditRating creditRating;
    private List<IProduct> products;
    private List<Request> requests;
    private List<Transaction> transactions;

    public Customer() {
    }

    public Customer(int customerId, UserRole userRole, String personId, String username, String password, String email,
            String name, String gender, int age, String address) {
        this.customerId = customerId;
        this.userRole = UserRole.CUSTOMER;
        this.personId = personId;            
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.address = address;
    }

    public int getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    // get a rating from existing records
    public CreditRating getCreditRating() {
        return this.creditRating;
    }

    // return credit rating from a str
    public CreditRating toCreditRating(String ratingStr) {
        switch (ratingStr) {
            case "A": {
                return CreditRating.A;
            }
            case "B": {
                return CreditRating.B;
            }
            case "C": {
                return CreditRating.C;
            }
            default: {
                return CreditRating.UNKNOWN;
            }
        }
    }

    public void setCreditRating(CreditRating creditRating) {
        this.creditRating = creditRating;
    }

    public List<IProduct> getProducts() {
        return this.products;
    }

    public void setProducts(List<IProduct> products) {
        this.products = products;
    }

    public List<Request> getRequests() {
        return this.requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public List<Transaction> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "{" +
                " customerId='" + getCustomerId() + "'" +
                " customerName='" + getName() + "'" +
                ", userRole='" + getUserRole() + "'" +
                ", username='" + getUsername() + "'" +
                ", password='" + getPassword() + "'" +
                ", creditRating='" + getCreditRating() + "'" +
                ", products='" + getProducts() + "'" +
                ", requests='" + getRequests() + "'" +
                ", transactions='" + getTransactions() + "'" +
                "} \n";
    }

}
