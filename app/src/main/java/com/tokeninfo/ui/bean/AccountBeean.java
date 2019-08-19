package com.tokeninfo.ui.bean;

public class AccountBeean {

    /**
     * Symbol : btc
     * Usd : 984.828
     * Total : 984.828
     * Token : 0
     * Id : 1
     */
    private String Symbol;
    private float Usd;
    private float Total;
    private float Token;

    public void setSymbol(String Symbol) {
        this.Symbol = Symbol;
    }

    public void setUsd(float Usd) {
        this.Usd = Usd;
    }

    public void setTotal(float Total) {
        this.Total = Total;
    }

    public void setToken(int Token) {
        this.Token = Token;
    }

    public String getSymbol() {
        return Symbol;
    }

    public float getUsd() {
        return Usd;
    }

    public float getTotal() {
        return Total;
    }

    public float getToken() {
        return Token;
    }
}
