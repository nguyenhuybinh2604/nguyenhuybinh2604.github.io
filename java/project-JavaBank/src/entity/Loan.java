package entity;

import java.time.LocalDate;

public class Loan extends Product {
    private int loanId;

    public Loan() {
    }

    public Loan(int customerId, Integer staffId, LocalDate valueDate, LocalDate maturityDate, Integer tenor,
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
        this.productType = ProductType.LOAN;
    }

    @Override
    public int getProductId() {
        return this.loanId;
    }

    @Override
    public void setProductId(int productId) {
        this.loanId = productId;
    }

    @Override
    public String toString() {
        return "{" +
                " loanId='" + getProductId() + "'" +
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
