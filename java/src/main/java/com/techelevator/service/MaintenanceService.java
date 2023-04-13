package com.techelevator.service;

import com.techelevator.model.Maintenance;
import com.techelevator.model.MaintenanceStatus;

import java.util.List;

public interface MaintenanceService {
    List<Maintenance> getAllRequests();

    Maintenance getRequestById(int id);

    Maintenance createRequest(Maintenance request, String username);

    boolean assignRequest(int requestId, int assignedTo, String username);

    boolean updateRequestStatus(int requestId, int statusId, String username);

    void deleteRequest(int id, String username);
}
