package entity;

import java.time.LocalDate;

public class Account extends Product {
    private int accountId;

    public Account() {
    }

    public Account(int customerId, Integer staffId, LocalDate valueDate, LocalDate maturityDate, Integer tenor, String currency, double balance, double convertedBalance, double interestRate, ProductStatus productStatus) {
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
        this.productType = ProductType.ACCOUNT;
    }

    @Override
    public int getProductId() {
        return this.accountId;
    }

    ;

    @Override
    public void setProductId(int productId) {
        this.accountId = productId;
    }

    ;

    @Override
    public String toString() {
        return "{" +
                " accountId='" + getProductId() + "'" +
                ", valueDate='" + getValueDate() + "'" +
                ", maturityDate='" + getMaturityDate() + "'" +
                ", currency='" + getCurrency() + "'" +
                ", balance='" + getBalance() + "'" +
                ", convertedBalance='" + getConvertedBalance() + "'" +
                ", interestRate='" + getInterestRate() + "'" +
                "} \n";
    }
}
