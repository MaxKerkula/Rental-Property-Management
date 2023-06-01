package com.techelevator.service;

import com.techelevator.model.MaintenanceRequest;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

public interface MaintenanceRequestService {
    List<MaintenanceRequest> getAllRequests();
    public List<MaintenanceRequest> getCurrentUserRequests(Principal principal);

    MaintenanceRequest getRequestById(int id);

    MaintenanceRequest createRequest(MaintenanceRequest request);

    public boolean updateRequest(int requestId, MaintenanceRequest updatedRequest, Principal principal);

    boolean deleteRequest(int maintenanceRequest_request_id );

    List<MaintenanceRequest> getAllRequestsById(int id);
}