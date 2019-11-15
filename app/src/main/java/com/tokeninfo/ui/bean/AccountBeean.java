package com.tokeninfo.ui.bean;

public class AccountBeean {


    /**
     * Symbol : btc
     * Rate : 0
     * Total : 0.1
     * Buy : 0.0019999999
     */
    private String Symbol;
    private float Rate;
    private float Total;
    private float Buy;

    public void setSymbol(String Symbol) {
        this.Symbol = Symbol;
    }

    public void setRate(int Rate) {
        this.Rate = Rate;
    }

    public void setTotal(float Total) {
        this.Total = Total;
    }

    public void setBuy(float Buy) {
        this.Buy = Buy;
    }

    public String getSymbol() {
        return Symbol;
    }

    public float getRate() {
        return Rate;
    }

    public float getTotal() {
        return Total;
    }

    public float getBuy() {
        return Buy;
    }
}
