package entity;

import java.time.LocalDate;

public class Saving extends Product {
    private int savingId;

    public Saving() {
    }

    public Saving(int customerId, Integer staffId, LocalDate valueDate, LocalDate maturityDate, Integer tenor,
                  String currency, double balance, double convertedBalance, double interestRate,
                  ProductStatus productStatus) {
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
        this.productType = ProductType.SAVING;
    }

    @Override
    public int getProductId() {
        return this.savingId;
    }
    ;

    @Override
    public void setProductId(int productId) {
        this.savingId = productId;
    }
    ;

    @Override
    public String toString() {
        return "{" +
                " savingId='" + getProductId() + "'" +
                ", customerId='" + getCustomerId() + "'" +
                ", staffId='" + getStaffId() + "'" +
                ", valueDate='" + getValueDate() + "'" +
                ", maturityDate='" + getMaturityDate() + "'" +
                ", currency='" + getCurrency() + "'" +
                ", balance='" + getBalance() + "'" +
                ", convertedBalance='" + getConvertedBalance() + "'" +
                ", interestRate='" + getInterestRate() + "'" +
                "} \n";
    }
}
