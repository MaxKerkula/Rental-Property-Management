package com.techelevator.model;

import java.util.Objects;

public class Maintenance {
    private int maintenanceId;
    private String description;
    private int statusId;
    private int propertyId;
    private int maintenanceWorkerId;

    public Maintenance() {
    }

    public Maintenance(int maintenanceId, String description, int statusId, int propertyId, int maintenanceWorkerId) {
        this.maintenanceId = maintenanceId;
        this.description = description;
        this.statusId = statusId;
        this.propertyId = propertyId;
        this.maintenanceWorkerId = maintenanceWorkerId;
    }

    public int getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(int maintenanceId) {
        this.maintenanceId = maintenanceId;
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

    public void setMaintenanceWorkerId(int maintenanceWorkerId) {
        this.maintenanceWorkerId = maintenanceWorkerId;
    }
}
