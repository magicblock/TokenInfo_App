package com.tokeninfo.ui.bean;

public class RecordBean {

    /**
     * Opetation : 1
     * Price : 7800
     * Symbol : btc
     * Total : 996
     * Explain : [时间]: 2019-05-21 01:45:52, [买入] ,[当前价] 7800.000000,[Usd] 0.000000,[Token] 0.127692,[token-Usd] 996.000061,[total] 996.000061,[收益] 0.000000
     * CreateTime : 2019-05-21T01:45:52+08:00
     * Id : 1
     */
    private int Id;
    private int Operation;
    private float Price;
    private String Symbol;
    private float Total;
    private String Explain;
    private String CreateTime;
    private float Size;

    public void setOpetation(int Opetation) {
        this.Operation = Opetation;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public void setSymbol(String Symbol) {
        this.Symbol = Symbol;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public void setExplain(String Explain) {
        this.Explain = Explain;
    }

    public void setCreateTime(String CreateTime) {
        this.CreateTime = CreateTime;
    }

    public int getOperation() {
        return Operation;
    }

    public float getPrice() {
        return Price;
    }

    public String getSymbol() {
        return Symbol;
    }

    public float getTotal() {
        return Total;
    }

    public String getExplain() {
        return Explain;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public float getSize() {
        return Size;
    }

    public void setSize(float size) {
        Size = size;
    }
}
