package com.techelevator.dao;

import com.techelevator.model.Property;

import java.util.List;

public interface PropertyDao {

    List<Property> findAll();

    List<Property> findAllAvailableProperties();

    Property getPropertyById(int id);

    Property createProperty(Property property);

    boolean updateProperty(int propertyId, Property property);

    void deleteProperty(int propertyId);

}