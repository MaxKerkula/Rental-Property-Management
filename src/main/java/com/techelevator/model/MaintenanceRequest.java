package com.techelevator.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
public class MaintenanceRequest {
    private int maintenanceRequestId;

    @NotBlank(message = "Description is required.")
    private String description;

    @NotNull(message = "Status ID is required.")
    private Integer statusId;

    @NotNull(message = "Property ID is required.")
    private Integer propertyId;

    @NotNull(message = "Maintenance worker ID is required.")
    private Integer maintenanceWorkerId;

    public MaintenanceRequest() {
    }

    public MaintenanceRequest(int maintenanceRequestId, String description, int statusId, int propertyId, int maintenanceWorkerId) {
        this.maintenanceRequestId = maintenanceRequestId;
        this.description = description;
        this.statusId = statusId;
        this.propertyId = propertyId;
        this.maintenanceWorkerId = maintenanceWorkerId;
    }

    public int getMaintenanceRequestId() {
        return maintenanceRequestId;
    }

    public void setMaintenanceRequestId(int maintenanceRequestId) {
        this.maintenanceRequestId = maintenanceRequestId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public int getMaintenanceWorkerId() {
        return maintenanceWorkerId;
    }

    public void setMaintenanceWorkerId(int maintenanceRequestWorkerId) {
        this.maintenanceWorkerId = maintenanceRequestWorkerId;
    }
}

