package com.techelevator.service;
import com.techelevator.dao.UserDao;
import com.techelevator.dao.MaintenanceRequestDao;
import com.techelevator.model.MaintenanceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
@Transactional
public class MaintenanceRequestServiceImp implements MaintenanceRequestService {

    private MaintenanceRequestDao maintenanceRequestDao;
    private UserDao userDao;

    @Autowired
    public MaintenanceRequestServiceImp(MaintenanceRequestDao maintenanceRequestDao, UserDao userDao) {
        this.maintenanceRequestDao = maintenanceRequestDao;
        this.userDao = userDao;
    }

    @Override
    public List<MaintenanceRequest> getAllRequests() {
        return maintenanceRequestDao.getAllRequests();
    }

    @Override
    public List<MaintenanceRequest> getCurrentUserRequests(Principal principal){
        return maintenanceRequestDao.getAllRequestsById(userDao.findIdByUsername(principal.getName()));
    }

    @Override
    public MaintenanceRequest getRequestById(int id) {
        return maintenanceRequestDao.getRequestById(id);
    }


    //Require Multiple Roles with "||" or single with one has role.
    //@PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
    //Require Either Role
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LANDLORD', 'ROLE_USERS', 'ROLE_MAINTENANCE')")
    //        if(userRoleService.isCorrectRole("ROLE_USER", principal )){
    //            return maintenanceRequestDao.createRequest(newRequest, principal);
    //        } return 0;
    //     @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LANDLORD', 'ROLE_USERS', 'ROLE_MAINTENANCE')")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LANDLORD', 'ROLE_USERS')")
    @Override
    public MaintenanceRequest createRequest(MaintenanceRequest request) {
        return maintenanceRequestDao.createRequest(request);

    }

    @PreAuthorize("hasAnyRole('ROLE_LANDLORD', 'ROLE_ADMIN', 'ROLE_MAINTENANCE')")
    public boolean updateRequest(int requestId, MaintenanceRequest updatedRequest, Principal principal) {
        String userRole = userDao.getUserRoleByID(userDao.findIdByUsername(principal.getName()));

        if ("ROLE_MAINTENANCE".equals(userRole)){
            // Limit maintenance worker to updating status_id only
            return maintenanceRequestDao.updateMaintenanceRequestStatus(requestId, updatedRequest.getStatusId());
        } else {
            // Allow landlord and admin to update the entire request
            return maintenanceRequestDao.updateMaintenanceRequest(requestId, updatedRequest);
        }
    }



    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_LANDLORD')")
    @Override
    public boolean deleteRequest(int maintenanceRequest_request_id) {
        try {
            maintenanceRequestDao.deleteRequest(maintenanceRequest_request_id);
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public List<MaintenanceRequest> getAllRequestsById(int id) {
        return maintenanceRequestDao.getAllRequestsById(id);
    }
}
