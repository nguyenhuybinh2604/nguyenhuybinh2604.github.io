package entity;

import java.time.LocalDate;

public class Account implements IProduct {
    private String accountId;
    private LocalDate valueDate;
    private LocalDate maturityDate;
    private String currency;
    private String customerId;
    private String staffId;
    private double interestRate;
    private double balance;
    private double convertedBalance;

    public Account() {
    }

    @Override
    public String getProductId() {
        return this.accountId;
    };

    @Override
    public void setProductId(String productId) {
        this.accountId = productId;
    };
}
