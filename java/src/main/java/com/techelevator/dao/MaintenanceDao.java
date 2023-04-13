package com.techelevator.dao;

import com.techelevator.model.Maintenance;

import java.security.Principal;
import java.util.List;

public interface MaintenanceDao {

    List<Maintenance> getAllRequests();

    Maintenance getRequestById(int id);

    int createRequest(Maintenance newRequest, Principal principal);

    boolean assignRequest(int requestId, int assignedTo, Principal principal);

    boolean updateRequestStatus(int requestId, int statusId, Principal principal);

    void deleteRequest(int id, Principal principal);
}
