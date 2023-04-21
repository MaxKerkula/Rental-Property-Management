package com.techelevator.controller;

import com.techelevator.model.MaintenanceRequest;
import com.techelevator.service.MaintenanceRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<List<MaintenanceRequest>> getCurrentUserRequests(Principal principal) {
        List<MaintenanceRequest> requests = maintenanceRequestService.getCurrentUserRequests(principal);
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<MaintenanceRequest>> getAllRequests() {
        List<MaintenanceRequest> requests = maintenanceRequestService.getAllRequests();
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceRequest> getRequestById(@PathVariable int id) {
        MaintenanceRequest request = maintenanceRequestService.getRequestById(id);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    public List<MaintenanceRequest> getAllRequestsById(int id) {
        return maintenanceRequestService.getAllRequestsById(id);
    }

    @PostMapping("")
    public ResponseEntity<MaintenanceRequest> createRequest(@Valid @RequestBody MaintenanceRequest newRequest) {
        MaintenanceRequest createdRequest = maintenanceRequestService.createRequest(newRequest);
        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateRequest(@PathVariable int id, @Valid @RequestBody MaintenanceRequest updatedRequest, Principal principal) {
        boolean updated = maintenanceRequestService.updateRequest(id, updatedRequest, principal);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable int id) {
        maintenanceRequestService.deleteRequest(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
