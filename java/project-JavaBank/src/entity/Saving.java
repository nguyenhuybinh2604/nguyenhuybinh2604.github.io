package entity;

import handle.InputControl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    @Override
    public void setProductId(int productId) {
        this.savingId = productId;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmtDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String valDate = valueDate == null ? "" : valueDate.format(fmtDate);
        String matDate = maturityDate == null ? "" : maturityDate.format(fmtDate);
        return String.format("%-10d%-10d%12s%12s%,10d%10s%,30.2f%,30.2f%,7.2f%%%10s%10s", savingId, staffId,
                valDate, matDate, tenor, currency, balance,
                convertedBalance, interestRate*100, productStatus.toString(),
                productType.toString());
    }
}
