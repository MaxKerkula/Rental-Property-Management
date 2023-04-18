package com.techelevator.model;

import java.time.LocalDateTime;

public class Property {

    private int id;
    private int rentalPrice;
    private String address;
    private boolean isAvailable;
    private int landlordId;
    // TODO below should be changed to local date
    private LocalDateTime dueDate;
    public Property() {
    }
    public Property(int id, int rentalPrice, String address, boolean isAvailable, int landlordId, LocalDateTime dueDate) {
        this.id = id;
        this.rentalPrice = rentalPrice;
        this.address = address;
        this.isAvailable = isAvailable;
        this.landlordId = landlordId;
        this.dueDate = dueDate;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(int rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getLandlordId() {
        return landlordId;
    }

    public void setLandlordId(int landlordId) {
        this.landlordId = landlordId;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}