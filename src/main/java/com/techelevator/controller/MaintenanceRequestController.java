package com.techelevator.controller;
import com.techelevator.model.MaintenanceRequest;
import com.techelevator.service.MaintenanceRequestService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
@RequestMapping("/maintenance-requests")
public class MaintenanceRequestController {

    private final MaintenanceRequestService maintenanceRequestService;

    public MaintenanceRequestController(MaintenanceRequestService maintenanceRequestService) {
        this.maintenanceRequestService = maintenanceRequestService;

    }

    public List<MaintenanceRequest> getCurrentUserRequests(Principal principal){
        return maintenanceRequestService.getCurrentUserRequests(principal);
    }
    @GetMapping("")
    public List<MaintenanceRequest> getAllRequests() {
        return maintenanceRequestService.getAllRequests();
    }

    @GetMapping("/{id}")
    public MaintenanceRequest getRequestById(@PathVariable int id) {
        return maintenanceRequestService.getRequestById(id);
    }
    public List<MaintenanceRequest> getAllRequestsById(int id){
        return maintenanceRequestService.getAllRequestsById(id);
    }
    @PostMapping("")
    public MaintenanceRequest createRequest(@Valid @RequestBody MaintenanceRequest newRequest) {
        return maintenanceRequestService.createRequest(newRequest);
    }

    @PutMapping("/update/{id}")
    public boolean updateRequest(@PathVariable int id, @Valid @RequestBody MaintenanceRequest updatedRequest, Principal principal) {
        return maintenanceRequestService.updateRequest(id, updatedRequest, principal);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRequest(@PathVariable int id) {
        maintenanceRequestService.deleteRequest(id);
    }
}

//      Required Methods for MVP and Stretch, Complete Business Logic in Service:
//      GET /maintenanceRequest/ get all maintenanceRequest requests
//      GET /maintenanceRequest/{id}:get a maintenanceRequest request by ID
//      POST /maintenanceRequest/:create a maintenanceRequest request
//      PUT /maintenanceRequest/{id}: update a maintenanceRequest request
//      DELETE /maintenanceRequest/{id}: delete a maintenanceRequest request