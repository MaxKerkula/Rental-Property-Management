package com.techelevator.controller;


import com.techelevator.model.Property;
import com.techelevator.model.PropertyPhoto;
import com.techelevator.service.PropertyService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
@RequestMapping("/properties")

public class PropertyController {

    private PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    //TODO Need to remove authentication from this method
    @GetMapping("")
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }


    @PostMapping ("/new-property")
    public Property createProperty(@RequestBody Property property, Principal principal) {

        return propertyService.createProperty(property, principal);
    }

    @GetMapping("/{id}/delete-property")
    public void deleteProperty(@PathVariable int id, Principal principal) {
        propertyService.deleteProperty(id, principal);
    }

    @GetMapping("/{id}")
    public Property getPropertyById(@PathVariable int id) {
        return propertyService.getPropertyById(id);
    }


    @PutMapping("/{id}/update-property")
    public boolean updateProperty(@RequestBody Property property, Principal principal) {

        return propertyService.updateProperty(property, principal);
    }

    @PutMapping("/{id}/update-property-photo")
    public boolean updatePropertyPhoto(@PathVariable Property property, @RequestBody  PropertyPhoto propertyPhoto, Principal principal) {
        return propertyService.updatePropertyPhoto(property, propertyPhoto, principal);
    }

    @PutMapping("/{id}/delete-property-photo")
    public void deletePropertyPhoto(@PathVariable int id,Principal principal) {
        propertyService.deletePropertyPhoto(id, principal);
    }

    @GetMapping( "/new-property-photo")
    public PropertyPhoto addPropertyPhoto(@RequestBody PropertyPhoto propertyPhoto, Principal principal ) {

        return propertyService.addPropertyPhoto(propertyPhoto, principal);
    }

    @GetMapping("/{id}/photo")
    public PropertyPhoto getPhotoByPropertyId(@PathVariable int id) {
        return propertyService.getPhotoByPropertyId(id);
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