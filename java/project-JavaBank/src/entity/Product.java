package entity;

import java.time.LocalDate;

public abstract class Product {
    protected int customerId;
    protected Integer staffId;
    protected LocalDate valueDate;
    protected LocalDate maturityDate;
    protected Integer tenor;
    protected String currency;
    protected double balance;
    protected double convertedBalance;
    protected double interestRate;
    protected ProductStatus productStatus;
    protected ProductType productType;

    public Product() {
    }

    public Product(int customerId, Integer staffId, LocalDate valueDate, LocalDate maturityDate, Integer tenor, String currency,
                   double balance, double convertedBalance,
                   double interestRate, ProductStatus productStatus, ProductType productType) {
        this.customerId = customerId;
        this.staffId = staffId;
        this.valueDate = valueDate;
        this.maturityDate = maturityDate;
        this.tenor = tenor;
        this.currency = currency;
        this.balance = balance;
        this.convertedBalance = convertedBalance;
        this.interestRate = interestRate;
        this.productStatus = productStatus;
        this.productType = productType;
    }

    public abstract int getProductId();

    public abstract void setProductId(int productId);

    public int getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Integer getStaffId() {
        return this.staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public LocalDate getValueDate() {
        return this.valueDate;
    }

    public void setValueDate(LocalDate valueDate) {
        if (valueDate != null) this.valueDate = valueDate;
    }

    public LocalDate getMaturityDate() {
        return this.maturityDate;
    }

    public void setMaturityDate(LocalDate maturityDate) {
        if (maturityDate != null) this.maturityDate = maturityDate;
    }

    public Integer getTenor() {
        return this.tenor;
    }

    public void setTenor(Integer tenor) {
        this.tenor = tenor;
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

    public double getConvertedBalance() {
        return this.convertedBalance;
    }

    public void setConvertedBalance(double convertedBalance) {
        this.convertedBalance = convertedBalance;
    }

    public double getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public ProductStatus getProductStatus() {
        return this.productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    public ProductType getProductType() {
        return this.productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public abstract String toString();
}
