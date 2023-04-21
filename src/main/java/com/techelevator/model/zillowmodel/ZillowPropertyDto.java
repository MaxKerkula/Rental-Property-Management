package com.techelevator.model.zillowmodel;

public class ZillowPropertyDto {
    //TODO: @validations
    private String address;

    private double price;


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

