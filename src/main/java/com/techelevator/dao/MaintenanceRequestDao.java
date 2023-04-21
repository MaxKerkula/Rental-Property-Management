package com.techelevator.dao;

import com.techelevator.model.MaintenanceRequest;

import java.util.List;

public interface MaintenanceRequestDao {

    List<MaintenanceRequest> getAllRequests();

    List<MaintenanceRequest> getAllRequestsById(int userId);

    boolean updateMaintenanceRequestStatus(int requestId, int statusId);

    MaintenanceRequest getRequestById(int id);

    MaintenanceRequest createRequest(MaintenanceRequest newRequest);

    void deleteRequest(int id);

    boolean updateMaintenanceRequest(int requestId, MaintenanceRequest updatedRequest);
}
