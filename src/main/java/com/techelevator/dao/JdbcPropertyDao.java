package com.techelevator.dao;

import com.techelevator.model.Property;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPropertyDao implements PropertyDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcPropertyDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Property> findAll() {
        List<Property> properties = new ArrayList<>();
        String sql = "SELECT * FROM property";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            Property property = mapRowToProperty(results);
            properties.add(property);
        }

        return properties;
    }

    @Override
    public List<Property> findAllAvailableProperties() {
        List<Property> listedProperties = new ArrayList<>();
        String sql = "SELECT * FROM property WHERE available = true;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            listedProperties.add(mapRowToProperty(results));
        }
        return listedProperties;
    }

    @Override
    public Property getPropertyById(int propertyId) {
        Property property = null;
        String sql = "SELECT property_id, rental_price, address, available,landlord_id, due_date FROM property WHERE property_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, propertyId);
        if (results.next()) {
            property = (mapRowToProperty(results));
        }
        return property;
    }

    @Override
    public Property createProperty(Property property) {
        Property newProperty = null;
        String sql = "INSERT INTO property (rental_price, address, available, landlord_id, due_date) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING property_id;";
        int propertyId =
                jdbcTemplate.queryForObject(sql, Integer.class, property.getRentalPrice(),
                        property.getAddress(), property.getAvailable(), property.getLandlordId(), property.getDueDate());

        return property;

    }

    @Override
    public boolean updateProperty(int propertyId, Property property) {
        property.setId(propertyId); // Add this line to set the property object's ID to the path variable
        String sql = "UPDATE property SET rental_price = ?, address = ?, available = ?, landlord_id  = ?, due_date  = ?" +
                "WHERE property_id = ?;";
        return jdbcTemplate.update(sql, property.getRentalPrice(),
                property.getAddress(), property.getAvailable(), property.getLandlordId(), property.getDueDate(), property.getId()) == 1;
    }


    @Override
    public void deleteProperty(int propertyId) {
        String sql = "DELETE FROM property WHERE property_id = ?";
        jdbcTemplate.update(sql, propertyId);
    }

    private Property mapRowToProperty(SqlRowSet results) {
        Property property = new Property();
        property.setId(results.getInt("property_id"));
        property.setRentalPrice(results.getInt("rental_price"));
        property.setAvailable(results.getBoolean("available"));
        property.setAddress(results.getString("address"));
        property.setLandlordId(results.getInt("landlord_id"));
        if (results.getDate("due_date") != null) {
        property.setDueDate(results.getDate("dueDate").toLocalDate());
        }
        return property;
    }
}