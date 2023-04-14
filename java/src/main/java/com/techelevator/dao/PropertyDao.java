package com.techelevator.dao;

import com.techelevator.model.Property;

import java.util.List;

public interface PropertyDao {

    List<Property> listOfAllAvailableProperties();
    Property getProperty(int propertyId);

    Property createListing(Property property);

    boolean updateProperty(Property property);

    void deleteProperty(int propertyId);
}
