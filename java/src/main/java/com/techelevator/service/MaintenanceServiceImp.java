package com.techelevator.service;
import com.techelevator.dao.MaintenanceDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Maintenance;
import com.techelevator.model.User;
import com.techelevator.model.UserDetails;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceServiceImp implements MaintenanceService {

    private MaintenanceDao maintenanceDao;
    private UserDao userDao;

    public MaintenanceServiceImp(MaintenanceDao maintenanceDao, UserDao userDao) {
        this.maintenanceDao = maintenanceDao;
        this.userDao = userDao;
    }

    @Override
    public List<Maintenance> getAllRequests() {
        return maintenanceDao.getAllRequests();
    }

    @Override
    public Maintenance getRequestById(int requestId) {
        return maintenanceDao.getRequestById(requestId);
    }

    @Override
    public Maintenance createRequest(Maintenance request, String username) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        UserDetails userDetails = userDetailDao.getUserDetailsByUserId(user.getUserId());
        if (userDetails == null) {
            throw new EntityNotFoundException("User details not found.");
        }
        request.setPropertyId(userDetails.getPropertyId());
        return maintenanceDao.createRequest(request);
    }

    @Override
    public boolean assignRequest(int requestId, int assignedTo, String username) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        Maintenance request = maintenanceDao.getRequestById(requestId);
        if (request == null) {
            throw new EntityNotFoundException("Maintenance request not found.");
        }
        if (request.getMaintenanceWorkerId() != user.getUserId()) {
            throw new AccessDeniedException("Access denied.");
        }
        return maintenanceDao.assignRequest(requestId, assignedTo);
    }

    @Override
    public boolean updateRequestStatus(int requestId, int statusId, String username) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        Maintenance request = maintenanceDao.getRequestById(requestId);
        if (request == null) {
            throw new EntityNotFoundException("Maintenance request not found.");
        }
        if (request.getMaintenanceWorkerId() != user.getUserId() && !user.getRole().equals("landlord")) {
            throw new AccessDeniedException("Access denied.");
        }
        return maintenanceDao.updateRequestStatus(requestId, statusId);
    }

    @Override
    public void deleteRequest(int requestId, String username) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        Maintenance request = maintenanceDao.getRequestById(requestId);
        if (request == null) {
            throw new EntityNotFoundException("Maintenance request not found.");
        }
        if (request.getMaintenanceWorkerId() != user.getUserId() && !user.getRole().equals("landlord")) {
            throw new AccessDeniedException("Access denied.");
        }
        maintenanceDao.deleteRequest(requestId);
    }
}