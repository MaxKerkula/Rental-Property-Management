package com.techelevator.service;

import com.techelevator.dao.MaintenanceRequestDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.MaintenanceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.security.Principal;
import java.util.List;

@Service
@Transactional
public class MaintenanceRequestServiceImp implements MaintenanceRequestService {
    private final Logger LOGGER = LoggerFactory.getLogger(MaintenanceRequestServiceImp.class);
    private final MaintenanceRequestDao maintenanceRequestDao;
    private final UserDao userDao;

    @Autowired
    public MaintenanceRequestServiceImp(MaintenanceRequestDao maintenanceRequestDao, UserDao userDao) {
        this.maintenanceRequestDao = maintenanceRequestDao;
        this.userDao = userDao;

    }

    @Override
    public List<MaintenanceRequest> getAllRequests() {
        try {
            return maintenanceRequestDao.getAllRequests();
        } catch (DataAccessException e) {
            LOGGER.error("Error occurred while retrieving all maintenance requests", e);
            throw new IllegalStateException("Could not retrieve maintenance requests.");
        }
    }

    @Override
    public List<MaintenanceRequest> getCurrentUserRequests(Principal principal) {
        try {
            return maintenanceRequestDao.getAllRequestsById(userDao.findIdByUsername(principal.getName()));
        } catch (DataAccessException e) {
            LOGGER.error("Error occurred while retrieving current user maintenance requests", e);
            throw new IllegalStateException("Could not retrieve current user maintenance requests.");
        }
    }

    @PreAuthorize("hasRole('ROLE_LANDLORD') || hasRole('ROLE_ADMIN') || hasRole('ROLE_MAINTENANCE')")
    @Override
    public MaintenanceRequest getRequestById(int id) {
        return maintenanceRequestDao.getRequestById(id);
    }


    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
    @Override
    public MaintenanceRequest createRequest(MaintenanceRequest request) {
        try {
            return maintenanceRequestDao.createRequest(request);
        } catch (DataAccessException e) {
            LOGGER.error("Error occurred while creating maintenance request", e);
            throw new IllegalArgumentException("Could not create maintenance request.");
        }
    }

    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
    public boolean updateRequest(int requestId, MaintenanceRequest updatedRequest, Principal principal) {
        String userRole = userDao.getUserRoleByID(userDao.findIdByUsername(principal.getName()));

        if ("ROLE_MAINTENANCE".equals(userRole)) {
            // Limit maintenance worker to updating status_id only
            return maintenanceRequestDao.updateMaintenanceRequestStatus(requestId, updatedRequest.getStatusId());
        } else {
            // Allow landlord and admin to update the entire request
            return maintenanceRequestDao.updateMaintenanceRequest(requestId, updatedRequest);
        }
    }

    @PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
    @Override
    public void deleteRequest(int maintenanceRequest_request_id) {
        try {
            maintenanceRequestDao.deleteRequest(maintenanceRequest_request_id);
        } catch (DataAccessException e) {
            LOGGER.error("Error occurred while deleting maintenance request with ID: {}", maintenanceRequest_request_id, e);
            throw new IllegalArgumentException("Could not delete maintenance request.");
        }
    }

    @Override
    public List<MaintenanceRequest> getAllRequestsById(int id) {
        try {
            return maintenanceRequestDao.getAllRequestsById(id);
        } catch (DataAccessException e) {
            LOGGER.error("Error occurred while retrieving maintenance requests by ID: {}", id, e);
            throw new IllegalStateException("Could not retrieve maintenance requests by ID.");
        }
    }
}
//Require Multiple Roles with "||" or single with one has role.
//@PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
//Require Either Role
//@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LANDLORD', 'ROLE_USERS', 'ROLE_MAINTENANCE')")
//        if(userRoleService.isCorrectRole("ROLE_USER", principal )){
//            return maintenanceRequestDao.createRequest(newRequest, principal);
//        } return 0;
//     @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LANDLORD', 'ROLE_USERS', 'ROLE_MAINTENANCE')")