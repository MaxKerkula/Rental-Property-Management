package com.techelevator.service;

import com.techelevator.model.Maintenance;

import java.security.Principal;
import java.util.List;

public interface MaintenanceService {
    List<Maintenance> getAllRequests();

    Maintenance getRequestById(int id, Principal principal);

    int createRequest(Maintenance request, Principal principal);

    boolean assignRequest(int requestId, int assignedTo, Principal principal);

    boolean updateRequestStatus(int requestId, int statusId, Principal principal);

    boolean deleteRequest(int id, Principal principal);

}
