package com.techelevator.service;

import com.techelevator.dao.PropertyPhotoDao;
import com.techelevator.dao.PropertyDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Property;
import com.techelevator.model.PropertyPhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;


import java.security.Principal;
import java.util.List;

@Service
public class PropertyServiceImp implements PropertyService{
    private PropertyDao propertyDao;
    private UserDao userDao;
    private PropertyPhotoDao propertyPhotoDao;

    @Autowired
    public PropertyServiceImp(PropertyDao propertyDao, UserDao userDao) {
        this.propertyDao = propertyDao;
        this.userDao = userDao;
    }

    @Override
    public List<Property> getAllProperties(){
        return propertyDao.findAll();
    }

    @Override
    public Property createProperty(Property property, Principal principal) {
        int loggedInUserId = userDao.findIdByUsername(principal.getName());
        String loggedInUserRole = userDao.getUserRoleByID(loggedInUserId);

//        if (loggedInUserRole.equals("ROLE_ADMIN")){
            return propertyDao.createProperty(property);
//        } else {
//            System.out.println("You need admin permissions to add a property");
//        }
//
//        return null;

    }

    @Override
    public void deleteProperty(int id, Principal principal) {
        int loggedInUserId = userDao.findIdByUsername(principal.getName());
        String loggedInUserRole = userDao.getUserRoleByID(loggedInUserId);

        if (loggedInUserRole.equals("ROLE_ADMIN")){
            propertyDao.deleteProperty(id);
        } else {
            System.out.println("You need admin permissions to delete a property");
        }

    }

    @Override
    public Property getPropertyById(int id) {
        return propertyDao.getPropertyById(id);
    }

    @Override
    public boolean updateProperty(Property property, Principal principal) {

        int loggedInUserId = userDao.findIdByUsername(principal.getName());
        String loggedInUserRole = userDao.getUserRoleByID(loggedInUserId);

        if (loggedInUserRole.equals("ROLE_ADMIN")){
            return propertyDao.updateProperty(property);
        } else {
            System.out.println("You need admin permissions to update a property");
        }
        return true;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public boolean updatePropertyPhoto(PropertyPhoto propertyPhoto, Principal principal) {
            return propertyPhotoDao.updatePropertyPhoto(propertyPhoto);

    }
    @Override
        public boolean updatePropertyPhoto(Property property, PropertyPhoto propertyPhoto, Principal principal) {
            return false;
        }

    @Override
    public void deletePropertyPhoto(int id, Principal principal) {
        int loggedInUserId = userDao.findIdByUsername(principal.getName());
        String loggedInUserRole = userDao.getUserRoleByID(loggedInUserId);

        if (loggedInUserRole.equals("ROLE_ADMIN")){
            propertyPhotoDao.deletePropertyPhoto(id);
        } else {
            System.out.println("You need admin permissions to delete a property photo");
        }
    }

    public PropertyPhoto addPropertyPhoto(PropertyPhoto propertyPhoto, Principal principal) {
        int loggedInUserId = userDao.findIdByUsername(principal.getName());
        String loggedInUserRole = userDao.getUserRoleByID(loggedInUserId);

        if (loggedInUserRole.equals("ROLE_ADMIN")){
            return propertyPhoto;
        } else {
            System.out.println("You need admin permissions to add a property");
        }

        return null;
    }

    @Override
    public PropertyPhoto getPhotoByPropertyId(int id) {
        return null;
    }

    @Override
    public List<PropertyPhoto> getAllPropertyPhotos() {
        return propertyPhotoDao.getAllPropertyPhotos();
    }
}