package com.techelevator.controller;

import com.techelevator.model.Maintenance;
import com.techelevator.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public Maintenance getRequestById(@PathVariable int id){
        return maintenanceService.getRequestById(id);
    }

    @PostMapping
    public Maintenance createRequest(@Valid @RequestBody Maintenance newRequest, @RequestParam String username){
        return maintenanceService.createRequest(newRequest, username);
    }

    @PutMapping("/assign/{requestId}")
    public boolean assignRequest(@PathVariable int requestId, @RequestParam int assignedTo, @RequestParam String username) {
        return maintenanceService.assignRequest(requestId, assignedTo, username);
    }

    @PutMapping("/status/{requestId}")
    public boolean updateRequestStatus(@PathVariable int requestId, @RequestParam int statusId, @RequestParam String username) {
        return maintenanceService.updateRequestStatus(requestId, statusId, username);
    }

    @DeleteMapping("/{id}")
    public void deleteRequest(@PathVariable int id, @RequestParam String username) {
        maintenanceService.deleteRequest(id, username);
    }
}
