package com.techelevator.service;

import com.techelevator.dao.PropertiesDAO;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Property;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.Properties;

public class PropertyServiceImplementation implements PropertyService{
    private PropertiesDAO propertiesDAO;
    private UserDao userDao;

    public PropertyServiceImplementation(PropertiesDAO propertiesDAO, UserDao userDao) {
        this.propertiesDAO = propertiesDAO;
        this.userDao = userDao;
    }

    @Override
    public List<Property> getAllProperties(){
        return propertiesDAO.listOfAllAvailableProperties();
    }

    @Override
    public Property createProperty(Property property, Principal principal) {
        int loggedInUserId = userDao.findIdByUsername(principal.getName());
        String loggedInUserRole = userDao.getUserRoleByID(loggedInUserId);

        if (loggedInUserRole.equals("ROLE_ADMIN")){
           return propertiesDAO.createListing(property);
        } else {
            System.out.println("You need admin permissions to add a property");
        }

    return null;

    }


}
