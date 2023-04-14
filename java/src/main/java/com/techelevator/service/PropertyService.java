package com.techelevator.service;

import com.techelevator.model.Property;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.List;
import java.util.Properties;

public interface PropertyService {
    public List<Property> getAllProperties();
    public Property createProperty(Property property, Principal principal);
}

