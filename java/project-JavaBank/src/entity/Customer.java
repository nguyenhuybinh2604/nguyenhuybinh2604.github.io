package entity;

import java.util.List;

import service.IUser;

public class Customer extends Person implements IUser {
    private int customerId;
    private UserRole userRole;
    private UserStatus userStatus;
    private String username;
    private String password;
    private String email;
    private CreditRating creditRating;
    private List<Product> products;
    private List<Request> requests;
    private List<Transaction> transactions;

    public Customer() {
    }

    public Customer(int customerId, String personId, String username, String password, String email,
                    String name, String gender, int age, String address, UserStatus userStatus) {
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
        this.userStatus = userStatus;
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

    @Override
    public UserStatus getUserStatus() {
        return this.userStatus;
    }

    @Override
    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    // get a rating from existing records
    public CreditRating getCreditRating() {
        return this.creditRating;
    }

    public void setCreditRating(CreditRating creditRating) {
        this.creditRating = creditRating;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void setProducts(List<Product> products) {
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

    public double getOutstandingLoanBalance() {
        double returnValue = 0;
        for (Product product : products) {
            if (product.getProductStatus() == ProductStatus.ACTIVE && product.getProductType() == ProductType.LOAN)
                returnValue = returnValue + product.getConvertedBalance();
        }
        return returnValue;
    }

    public double getOutstandingDepositBalance() {
        double returnValue = 0;
        for (Product product : products) {
            if (product.getProductStatus() == ProductStatus.ACTIVE &&
                    (product.getProductType() == ProductType.SAVING || product.getProductType() == ProductType.ACCOUNT))
                returnValue = returnValue + product.getConvertedBalance();
        }
        return returnValue;
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
