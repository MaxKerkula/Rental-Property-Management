package com.techelevator.dao;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.techelevator.model.Property;

import java.util.List;

public interface PropertyDao {

    List<Property> findAll();

    List<Property> findAllAvailableProperties();

    Property getPropertyById(int id);

    Property createProperty(Property property);

    boolean updateProperty(Property property);

    void deleteProperty(int propertyId);

}