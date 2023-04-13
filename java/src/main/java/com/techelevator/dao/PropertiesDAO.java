package com.techelevator.dao;

import com.techelevator.model.Property;

import java.util.List;

public interface PropertiesDAO {

        List<Property> listOfAllAvailableProperties();

        boolean updateProperty(int propertyId);

       Property getProperty(int propertyId);

        Property createListing(Property listing);

        Property saveProperty(Property property);

        void deleteProperty(int propertyId);
}
