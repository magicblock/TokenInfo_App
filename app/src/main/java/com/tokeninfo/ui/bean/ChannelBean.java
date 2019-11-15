package com.tokeninfo.ui.bean;

public class ChannelBean {


    /**
     * High : 9010
     * Add : 8499.194
     * Low : 8613
     * Stop : 8907.27
     * Symbol : btc
     */
    private String Symbol;
    private float High;
    private float Add;
    private float Low;
    private float Stop;
    private int Op;
    private float Price;

    public void setHigh(float High) {
        this.High = High;
    }

    public void setAdd(float Add) {
        this.Add = Add;
    }

    public void setLow(float Low) {
        this.Low = Low;
    }

    public void setStop(float Stop) {
        this.Stop = Stop;
    }

    public void setSymbol(String Symbol) {
        this.Symbol = Symbol;
    }

    public float getHigh() {
        return High;
    }

    public float getAdd() {
        return Add;
    }

    public float getLow() {
        return Low;
    }

    public float getStop() {
        return Stop;
    }

    public String getSymbol() {
        return Symbol;
    }

    public int getOp() {
        return Op;
    }

    public void setOp(int op) {
        Op = op;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }
}
