package com.techelevator.dao;

import com.techelevator.model.Maintenance;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

public interface MaintenanceDao {

    List<Maintenance> getAllRequests();

    Maintenance getRequestById(int requestId);

    Maintenance createRequest(Maintenance request);

    boolean assignRequest(int requestId, int assignedTo);

    boolean updateRequestStatus(int requestId, int statusId);

    void deleteRequest(int id);


}
