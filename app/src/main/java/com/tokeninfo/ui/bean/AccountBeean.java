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
    private double Usd;
    private double Total;
    private int Token;
    private int Id;

    public void setSymbol(String Symbol) {
        this.Symbol = Symbol;
    }

    public void setUsd(double Usd) {
        this.Usd = Usd;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public void setToken(int Token) {
        this.Token = Token;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getSymbol() {
        return Symbol;
    }

    public double getUsd() {
        return Usd;
    }

    public double getTotal() {
        return Total;
    }

    public int getToken() {
        return Token;
    }

    public int getId() {
        return Id;
    }
}
