package com.tokeninfo.ui.bean;

import java.io.Serializable;

public class TargetBean implements Serializable {


    /**
     * Id : 12
     * Symbol : btc
     * Price : 4000
     */

    private String Symbol;
    private float Price;

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String Symbol) {
        this.Symbol = Symbol;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float Price) {
        this.Price = Price;
    }
}
