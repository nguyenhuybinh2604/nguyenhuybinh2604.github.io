package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private int transactionId;
    private LocalDateTime transactionTime;
    private TransactionType transactionType;
    private int accountId;
    private int customerId;
    private String currency;
    private double credit;
    private double debit;
    private double convertedCredit;
    private double convertedDebit;

    public Transaction() {
    }

    public Transaction(int transactionId, LocalDateTime transactionTime, TransactionType transactionType, int accountId, int customerId,
                       String currency, double credit, double debit, double convertedCredit, double convertedDebit) {
        this.transactionId = transactionId;
        this.transactionTime = transactionTime;
        this.transactionType = transactionType;
        this.accountId = accountId;
        this.customerId = customerId;
        this.currency = currency;
        this.credit = credit;
        this.debit = debit;
        this.convertedCredit = convertedCredit;
        this.convertedDebit = convertedDebit;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public void setTransactionType(String transactionTypeStr) {
        if (transactionTypeStr.equalsIgnoreCase("BALANCE")) this.transactionType = TransactionType.ADDBALANCE;
        else if (transactionTypeStr.equalsIgnoreCase("FT")) this.transactionType = TransactionType.FUNDTRANSFER;
        else if (transactionTypeStr.equalsIgnoreCase("FX")) this.transactionType = TransactionType.FOREIGNEXCHANGE;
        else this.transactionType = TransactionType.NA;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public double getConvertedCredit() {
        return convertedCredit;
    }

    public void setConvertedCredit(double convertedCredit) {
        this.convertedCredit = convertedCredit;
    }

    public double getConvertedDebit() {
        return convertedDebit;
    }

    public void setConvertedDebit(double convertedDebit) {
        this.convertedDebit = convertedDebit;
    }

    //    @Override
    public String toString(DateTimeFormatter formatter) {
        return String.format("%-10d%-20s%-10s%-12d%-14d%-10s%,30.2f%,30.2f%,30.2f%,30.2f", transactionId, transactionTime.format(formatter),
                transactionType.toString(), accountId, customerId, currency, credit,
                debit, convertedCredit, convertedDebit);
    }
}
