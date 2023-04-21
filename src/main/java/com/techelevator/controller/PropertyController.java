package com.techelevator.controller;


import com.techelevator.model.Property;
import com.techelevator.model.PropertyPhoto;
import com.techelevator.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
@RequestMapping("/properties")

public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping("")
    public ResponseEntity<List<Property>> getAllProperties() {
        List<Property> properties = propertyService.getAllProperties();
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @PostMapping("/new-property")
    public ResponseEntity<Property> createProperty(@RequestBody Property property, Principal principal) {
        Property newProperty = propertyService.createProperty(property, principal);
        return new ResponseEntity<>(newProperty, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/delete-property")
    public ResponseEntity<Void> deleteProperty(@PathVariable int id, Principal principal) {
        propertyService.deleteProperty(id, principal);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getPropertyById(@PathVariable int id) {
        Property property = propertyService.getPropertyById(id);
        return new ResponseEntity<>(property, HttpStatus.OK);
    }

    @PutMapping("/{propertyId}/update-property")
    public ResponseEntity<Boolean> updateProperty(@PathVariable int propertyId, @RequestBody Property property) {
        boolean updated = propertyService.updateProperty(propertyId, property);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @PutMapping("/{id}/update-property-photo")
    public ResponseEntity<Boolean> updatePropertyPhoto(@PathVariable Property property, @RequestBody PropertyPhoto propertyPhoto, Principal principal) {
        boolean isUpdated = propertyService.updatePropertyPhoto(property, propertyPhoto, principal);
        return new ResponseEntity<>(isUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete-property-photo")
    public ResponseEntity<Void> deletePropertyPhoto(@PathVariable int id, Principal principal) {
        propertyService.deletePropertyPhoto(id, principal);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/new-property-photo")
    public ResponseEntity<PropertyPhoto> addPropertyPhoto(@RequestBody PropertyPhoto propertyPhoto, Principal principal) {
        PropertyPhoto newPropertyPhoto = propertyService.addPropertyPhoto(propertyPhoto, principal);
        return new ResponseEntity<>(newPropertyPhoto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/photo")
    public ResponseEntity<PropertyPhoto> getPhotoByPropertyId(@PathVariable int id) {
        PropertyPhoto propertyPhoto = propertyService.getPhotoByPropertyId(id);
        return new ResponseEntity<>(propertyPhoto, HttpStatus.OK);
    }

}


//      Required Methods for MVP and Stretch, Complete Business Logic in Service:
//      GET /properties/{id}: get a property by ID
//      GET /properties/: get all properties
//      GET /properties/{propertyId}/photos: get photos by property ID
//      POST /properties/: create a property
//      POST /properties/{propertyId}/photos: add a photo
//      PUT /properties/{id}: update a property
//      PUT /properties/{propertyId}/photos/{id}: update a photo
//      DELETE /properties/{id}: delete a property
//      DELETE/properties/{propertyId}/photos/{id}: delete a photo