package com.techelevator.model;

import java.util.Objects;

public class Property {

    private int id;
    private int rentalPrice;
    private String address;
    private boolean isAvailable;
    private int landlordId;


    public Property(int id, int rentalPrice, String address, boolean isAvailable, int landlordId) {
        this.id = id;
        this.rentalPrice = rentalPrice;
        this.address = address;
        this.isAvailable = isAvailable;
        this.landlordId = landlordId;
    }

    public Property() {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Property)) return false;
        Property property = (Property) o;
        return getId() == property.getId() && getRentalPrice() == property.getRentalPrice() && isAvailable() == property.isAvailable() && getLandlordId() == property.getLandlordId() && getAddress().equals(property.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getRentalPrice(), getAddress(), isAvailable(), getLandlordId());
    }

    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", rentalPrice=" + rentalPrice +
                ", address='" + address + '\'' +
                ", isAvailable=" + isAvailable +
                ", landlordId=" + landlordId +
                '}';
    }
}