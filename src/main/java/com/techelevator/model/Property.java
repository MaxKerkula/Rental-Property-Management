package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

public class Property {

    private int id;
    @NotNull(message = "Rental price is required.")
    @Positive(message = "Rental price must be positive.")
    private Integer rentalPrice;

    @NotBlank(message = "Address is required.")
    private String address;

    @NotNull(message = "Availability status is required.")
    private Boolean available;

    @NotNull(message = "Landlord ID is required.")
    private Integer landlordId;

    @NotNull(message = "Due date is required.")
    private LocalDate dueDate;

    public Property() {
    }

    public Property(int id, int rentalPrice, String address, boolean available, int landlordId, LocalDate dueDate) {
        this.id = id;
        this.rentalPrice = rentalPrice;
        this.address = address;
        this.available = available;
        this.landlordId = landlordId;
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(Integer rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Integer getLandlordId() {
        return landlordId;
    }

    public void setLandlordId(Integer landlordId) {
        this.landlordId = landlordId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}