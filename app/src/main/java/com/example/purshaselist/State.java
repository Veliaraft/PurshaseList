package com.example.purshaselist;

public class State {
    private String PRODUCT;
    private final String DATE;
    private final String AMOUNT;
    private final String COST;

    public State(String PRODUCT,String DATE,String AMOUNT,String COST){
        this.PRODUCT = PRODUCT;
        this.DATE = DATE;
        this.AMOUNT = AMOUNT;
        this.COST = COST;
    }

    public String getPRODUCT() {
        return this.PRODUCT;
    }
    public String getDATE(){return this.DATE;}
    public String getAMOUNT(){return this.AMOUNT;}
    public String getCOST(){return this.COST;}
    public void setName(String PRODUCT) {
        this.PRODUCT = PRODUCT;
    }

}