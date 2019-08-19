package com.tokeninfo.ui.bean;

public class MarginAccountBean {

    /**
     * Used : 0
     * Symbol : btc
     * Total : 1000
     * Id : 1
     * Balance : 1000
     */
    private float Buy;
    private String Symbol;
    private float Total;
    private int Id;
    private float Balance;

    public float getBuy() {
        return Buy;
    }

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    public float getTotal() {
        return Total;
    }

    public void setTotal(float total) {
        Total = total;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public float getBalance() {
        return Balance;
    }

    public void setBalance(float balance) {
        Balance = balance;
    }
}
