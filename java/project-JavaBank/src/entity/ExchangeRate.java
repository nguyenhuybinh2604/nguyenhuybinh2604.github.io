package entity;

import java.time.LocalDate;

public class ExchangeRate {
    private LocalDate effectDate;
    private String fromCurrency;
    private String toCurrency;
    private double exchangeRate;

    public ExchangeRate() {
    }

    public ExchangeRate(LocalDate effectDate, String fromCurrency, String toCurrency, double exchangeRate) {
        this.effectDate = effectDate;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.exchangeRate = exchangeRate;
    }

    public LocalDate getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(LocalDate effectDate) {
        this.effectDate = effectDate;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "effectDate=" + effectDate +
                ", fromCurrency='" + fromCurrency + '\'' +
                ", toCurrency='" + toCurrency + '\'' +
                ", exchangeRate=" + exchangeRate +
                '}';
    }
}
