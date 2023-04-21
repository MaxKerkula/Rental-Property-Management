package com.techelevator.service;

import com.techelevator.dao.PropertyDao;
import com.techelevator.dao.PropertyPhotoDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Property;
import com.techelevator.model.PropertyPhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.security.Principal;
import java.util.List;

@Service
public class PropertyServiceImp implements PropertyService {
    private final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImp.class);
    private final PropertyDao propertyDao;
    private final UserDao userDao;
    private PropertyPhotoDao propertyPhotoDao;

    @Autowired
    public PropertyServiceImp(PropertyDao propertyDao, UserDao userDao, PropertyPhotoDao propertyPhotoDao) {
        this.propertyDao = propertyDao;
        this.userDao = userDao;
        this.propertyPhotoDao = propertyPhotoDao;
    }

    @Override
    public List<Property> getAllProperties() {
        try {
            return propertyDao.findAll();
        } catch (DataAccessException e) {
            LOGGER.error("Error occurred while retrieving all properties", e);
            throw new IllegalStateException("Could not retrieve properties.");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public Property createProperty(Property property, Principal principal) {
        int loggedInUserId = userDao.findIdByUsername(principal.getName());
        String loggedInUserRole = userDao.getUserRoleByID(loggedInUserId);

        try {
            return propertyDao.createProperty(property);
        } catch (Exception e) {
            LOGGER.error("An error occurred when trying to create a new property", e);
            throw new IllegalStateException("Could not create property.");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void deleteProperty(int id, Principal principal) {
        int loggedInUserId = userDao.findIdByUsername(principal.getName());
        String loggedInUserRole = userDao.getUserRoleByID(loggedInUserId);

        try {
            propertyDao.deleteProperty(id);
        } catch (DataAccessException e) {
            LOGGER.error("Error occurred while deleting property with ID: {}", id, e);
            throw new IllegalArgumentException("Could not delete property.");
        }
    }

    @Override
    public Property getPropertyById(int id) {
        try {
            return propertyDao.getPropertyById(id);
        } catch (DataAccessException e) {
            LOGGER.error("Error occurred while retrieving property by ID: {}", id, e);
            throw new IllegalStateException("Could not retrieve property by ID.");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_LANDLORD')")
    @Override
    public boolean updateProperty(int propertyId, Property property) {
        try {
            return propertyDao.updateProperty(propertyId, property);
        } catch (DataAccessException e) {
            LOGGER.error("Error occurred while updating property with ID: {}", propertyId, e);
            throw new IllegalArgumentException("Could not update property.");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_LANDLORD')")
    @Override
    public boolean updatePropertyPhoto(Property property, PropertyPhoto propertyPhoto, Principal principal) {
        try {
            return propertyPhotoDao.updatePropertyPhoto(propertyPhoto);
        } catch (DataAccessException e) {
            LOGGER.error("Error occurred while updating property photo", e);
            throw new IllegalArgumentException("Could not update property photo.");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_LANDLORD')")
    @Override
    public void deletePropertyPhoto(int id, Principal principal) {
        try {
            propertyPhotoDao.deletePropertyPhoto(id);
        } catch (DataAccessException e) {
            LOGGER.error("Error occurred while deleting property photo with ID: {}", id, e);
            throw new IllegalArgumentException("Could not delete property photo.");
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_LANDLORD')")
    public PropertyPhoto addPropertyPhoto(PropertyPhoto propertyPhoto, Principal principal) {
        try {
            propertyPhotoDao.addPropertyPhoto(propertyPhoto);
            return propertyPhoto;
        } catch (DataAccessException e) {
            LOGGER.error("Error occurred while adding property photo", e);
            throw new IllegalStateException("Could not add property photo.");
        }
    }

    @Override
    public PropertyPhoto getPhotoByPropertyId(int id) {
        try {
            return propertyPhotoDao.getPropertyPhoto(id);
        } catch (DataAccessException e) {
            LOGGER.error("Error occurred while retrieving property photo by property ID: {}", id, e);
            throw new IllegalStateException("Could not retrieve property photo by property ID.");
        }
    }

    @Override
    public List<PropertyPhoto> getAllPropertyPhotos() {
        return propertyPhotoDao.getAllPropertyPhotos();
    }
}