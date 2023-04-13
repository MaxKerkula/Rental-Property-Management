package com.techelevator.controller;

import com.techelevator.dao.JdbcPropertiesDAO;
import com.techelevator.dao.PropertiesDAO;
import com.techelevator.dao.UserDao;
import com.techelevator.model.LoginDto;
import com.techelevator.model.Maintenance;
import com.techelevator.model.User;
import com.techelevator.model.Property;
import com.techelevator.service.MaintenanceService;
import com.techelevator.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.Properties;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
@RequestMapping("http://localhost:9000/properties")

public class PropertyController {

    private PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }


    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Property> getAllProperties(){
        return propertyService.getAllProperties();
    }


    @RequestMapping(path = "/newproperty", method = RequestMethod.POST)
    public Property createProperty(@RequestBody Property property, Principal principal) {

        return propertyService.createProperty(property, principal);
    }







}

