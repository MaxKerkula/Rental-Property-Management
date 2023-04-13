package com.techelevator.model;

import java.util.Objects;

public class Maintenance {
    private int id;
    private String description;
    private int statusId;
    private int propertyId;
    private int maintenanceWorkerId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Maintenance)) return false;
        Maintenance that = (Maintenance) o;
        return getId() == that.getId() && getStatusId() == that.getStatusId() && getPropertyId() == that.getPropertyId() && getMaintenanceWorkerId() == that.getMaintenanceWorkerId() && getDescription().equals(that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDescription(), getStatusId(), getPropertyId(), getMaintenanceWorkerId());
    }

    @Override
    public String toString() {
        return "Maintenance{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", statusId=" + statusId +
                ", propertyId=" + propertyId +
                ", maintenanceWorkerId=" + maintenanceWorkerId +
                '}';
    }
}
