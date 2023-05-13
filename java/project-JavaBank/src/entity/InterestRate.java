package entity;

import java.time.LocalDate;

public class InterestRate {
    private LocalDate effectDate;
    private String currency;
    private ProductType productType;
    private Integer tenor;
    private String creditRatingStr; // not all product price requires rating
    private double interestRate;

    public InterestRate() {

    }

    public InterestRate(LocalDate effectDate, String currency, ProductType productType, Integer tenor, String creditRatingStr, double interestRate) {
        this.effectDate = effectDate;
        this.currency = currency;
        this.productType = productType;
        this.tenor = tenor;
        this.creditRatingStr = creditRatingStr;
        this.interestRate = interestRate;
    }

    public LocalDate getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(LocalDate effectDate) {
        this.effectDate = effectDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public void setProductType(String productTypeStr) {
        if (productTypeStr.equalsIgnoreCase("ACCOUNT")) this.productType = ProductType.ACCOUNT;
        else if (productTypeStr.equalsIgnoreCase("LOAN")) this.productType = ProductType.LOAN;
        else if (productTypeStr.equalsIgnoreCase("SAVING")) this.productType = ProductType.SAVING;
        else this.productType = ProductType.NA;
    }

    public Integer getTenor() {
        return tenor;
    }

    public void setTenor(Integer tenor) {
        this.tenor = tenor;
    }

    public String getCreditRatingStr() {
        return creditRatingStr;
    }

    public void setCreditRatingStr(String creditRatingStr) {
        this.creditRatingStr = creditRatingStr;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public String toString() {
        return "InterestRate{" +
                "effectDate=" + effectDate +
                ", currency='" + currency + '\'' +
                ", productType=" + productType +
                ", tenor=" + tenor +
                ", creditRatingStr='" + creditRatingStr + '\'' +
                ", interestRate=" + interestRate +
                "} \n";
    }
}
