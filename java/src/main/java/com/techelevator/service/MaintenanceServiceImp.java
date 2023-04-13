package com.techelevator.service;

import com.techelevator.dao.MaintenanceDao;
import com.techelevator.model.Maintenance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class MaintenanceServiceImp implements MaintenanceService {

    private MaintenanceDao maintenanceDao;

    @Autowired
    public MaintenanceServiceImp(MaintenanceDao maintenanceDao) {
        this.maintenanceDao = maintenanceDao;
    }

    @Override
    public List<Maintenance> getAllRequests() {
        return maintenanceDao.getAllRequests();
    }

    @Override
    public Maintenance getRequestById(int id, Principal principal) {
        return maintenanceDao.getRequestById(id);
    }

    @Override
    public int createRequest(Maintenance newRequest, Principal principal) {
        return maintenanceDao.createRequest(newRequest, principal);
    }

    @Override
    public boolean assignRequest(int requestId, int assignedTo, Principal principal) {
        try {
            return maintenanceDao.assignRequest(requestId, assignedTo, principal);
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public boolean updateRequestStatus(int requestId, int statusId, Principal principal) {
        try {
            return maintenanceDao.updateRequestStatus(requestId, statusId, principal);
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public boolean deleteRequest(int id, Principal principal) {
        try {
            maintenanceDao.deleteRequest(id, principal);
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

}
