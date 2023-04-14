package com.techelevator.service;

import com.techelevator.dao.PropertyDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Property;

import java.security.Principal;
import java.util.List;

public class PropertyServiceImplementation implements PropertyService{
    private PropertyDao propertyDAO;
    private UserDao userDao;

    public PropertyServiceImplementation(PropertyDao propertyDAO, UserDao userDao) {
        this.propertyDAO = propertyDAO;
        this.userDao = userDao;
    }

    @Override
    public List<Property> getAllProperties(){
        return propertyDAO.listOfAllAvailableProperties();
    }

    @Override
    public Property createProperty(Property property, Principal principal) {
        int loggedInUserId = userDao.findIdByUsername(principal.getName());
        String loggedInUserRole = userDao.getUserRoleByID(loggedInUserId);

        if (loggedInUserRole.equals("ROLE_ADMIN")){
           return propertyDAO.createListing(property);
        } else {
            System.out.println("You need admin permissions to add a property");
        }

    return null;

    }


}
