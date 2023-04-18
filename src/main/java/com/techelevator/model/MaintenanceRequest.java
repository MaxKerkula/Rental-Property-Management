package com.techelevator.model;

public class MaintenanceRequest {
    private int maintenanceRequestId;
    private String description;
    private int statusId;
    private int propertyId;
    private int maintenanceWorkerId;

    public MaintenanceRequest() {
    }

    public MaintenanceRequest(int maintenanceRequestId, String description, int statusId, int propertyId, int maintenanceRequestWorkerId) {
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

