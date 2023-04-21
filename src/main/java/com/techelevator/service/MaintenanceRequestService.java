package com.techelevator.service;

import com.techelevator.model.MaintenanceRequest;

import java.security.Principal;
import java.util.List;

public interface MaintenanceRequestService {
    List<MaintenanceRequest> getAllRequests();

    List<MaintenanceRequest> getCurrentUserRequests(Principal principal);

    MaintenanceRequest getRequestById(int id);

    MaintenanceRequest createRequest(MaintenanceRequest request);

    boolean updateRequest(int requestId, MaintenanceRequest updatedRequest, Principal principal);

    void deleteRequest(int maintenanceRequest_request_id);

    List<MaintenanceRequest> getAllRequestsById(int id);
}