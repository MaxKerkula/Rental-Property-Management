package com.techelevator.model;

import java.util.Objects;

public class MaintenanceStatus {
    private int statusId;
    private String statusDescription;

    public MaintenanceStatus(int statusId, String statusDescription) {
        this.statusId = statusId;
        this.statusDescription = statusDescription;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MaintenanceStatus)) return false;
        MaintenanceStatus that = (MaintenanceStatus) o;
        return getStatusId() == that.getStatusId() && getStatusDescription().equals(that.getStatusDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatusId(), getStatusDescription());
    }

    @Override
    public String toString() {
        return "MaintenanceStatus{" +
                "status_Id=" + statusId +
                ", statusDescription='" + statusDescription + '\'' +
                '}';
    }

    // Constructors, getters, and setters
}
