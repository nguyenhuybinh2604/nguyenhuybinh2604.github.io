package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class BalanceSheet {
    private LocalDate effectDate;
    private double cashBalance;
    private double loanBalance;
    private double depositBalance;
    private double equityBalance;

    public BalanceSheet() {
        this.effectDate = LocalDate.now();
        this.equityBalance = 300 * Math.pow(10, 9);
        this.cashBalance = this.equityBalance + depositBalance - loanBalance;
    }

    public BalanceSheet(List<Product> products) {
        double loanBalance = 0.0;
        double depositBalance = 0.0;

        this.effectDate = LocalDate.now();
        this.equityBalance = 300 * Math.pow(10, 9);

        List<Product> activeProducts = products.stream()
                .filter(o -> o.getProductStatus() == ProductStatus.ACTIVE) //chi lay active
                .collect(Collectors.toList());
        for (Product product : activeProducts) {
            switch (product.getProductType()) {
                case LOAN:
                    loanBalance += product.getConvertedBalance();
                case SAVING:
                    depositBalance += product.getConvertedBalance();
                case ACCOUNT:
                    depositBalance += product.getConvertedBalance();
            }
        }
        this.cashBalance = this.equityBalance + depositBalance - loanBalance;
    }

    public LocalDate getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(LocalDate effectDate) {
        this.effectDate = effectDate;
    }

    public double getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(double cashBalance) {
        this.cashBalance = cashBalance;
    }

    public double getLoanBalance() {
        return loanBalance;
    }

    public void setLoanBalance(double loanBalance) {
        this.loanBalance = loanBalance;
    }

    public double getDepositBalance() {
        return depositBalance;
    }

    public void setDepositBalance(double depositBalance) {
        this.depositBalance = depositBalance;
    }

    public double getEquityBalance() {
        return equityBalance;
    }

    public void setEquityBalance(double equityBalance) {
        this.equityBalance = equityBalance;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmtDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("%s %s %s %,.2f %s %s %,.2f %s %,.2f %s %s %,.2f %s", "JavaBank Balance Sheet for ",
                effectDate.format(fmtDate), ":\nCash: ", cashBalance, "\n", "Outstanding loan balance: ", loanBalance,
                "\nOutstanding deposit balance: ", depositBalance, "\n", "Equity: ", equityBalance, "\n");
    }
}
