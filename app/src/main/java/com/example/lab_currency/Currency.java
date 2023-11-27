package com.example.lab_currency;


public class Currency {
    private String currencyCode;
    private double exchangeRate;

    public Currency(String currencyCode, double exchangeRate) {
        this.currencyCode = currencyCode;
        this.exchangeRate = exchangeRate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }


    public String getCurrencyName() {
        return currencyCode;
    }
}
