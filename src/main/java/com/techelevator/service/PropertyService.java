package com.techelevator.service;

import com.techelevator.model.Property;
import com.techelevator.model.PropertyPhoto;

import java.security.Principal;
import java.util.List;

public interface PropertyService {
    List<Property> getAllProperties();

    Property createProperty(Property property, Principal principal);

    void deleteProperty(int id, Principal principal);

    Property getPropertyById(int id);

    boolean updateProperty(int property, Property principal);

    boolean updatePropertyPhoto(Property property, PropertyPhoto propertyPhoto, Principal principal);

    void deletePropertyPhoto(int id, Principal principal);

    PropertyPhoto addPropertyPhoto(PropertyPhoto property, Principal principal);

    PropertyPhoto getPhotoByPropertyId(int id);

    List<PropertyPhoto> getAllPropertyPhotos();
}