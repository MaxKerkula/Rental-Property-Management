package com.techelevator.controller;

import com.techelevator.model.Maintenance;
import com.techelevator.service.MaintenanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/maintenance")
public class MaintenanceController {

    private MaintenanceService maintenanceService;

    @Autowired
    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @GetMapping
    public List<Maintenance> getAllRequests(){
        return maintenanceService.getAllRequests();
    }

    @GetMapping("/{id}")
    public Maintenance getRequestById(@PathVariable int id, Principal principal){
        return maintenanceService.getRequestById(id, principal);
    }

    @PostMapping
    public int createRequest(@Valid @RequestBody Maintenance newRequest, Principal principal){
        return maintenanceService.createRequest(newRequest, principal);
    }

    @PutMapping("/assign/{requestId}")
    public boolean assignRequest(@PathVariable int requestId, @RequestBody Integer assignedTo, Principal principal) {
        return maintenanceService.assignRequest(requestId, assignedTo, principal);
    }

    @PutMapping("/status/{requestId}")
    public boolean updateRequestStatus(@PathVariable int requestId, @RequestBody Integer statusId, Principal principal) {
        return maintenanceService.updateRequestStatus(requestId, statusId, principal);
    }

    @DeleteMapping("/{id}")
    public void deleteRequest(@PathVariable int id, Principal principal) {
        maintenanceService.deleteRequest(id, principal);
    }
}
