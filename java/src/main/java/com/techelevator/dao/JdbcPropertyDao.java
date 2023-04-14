package com.techelevator.dao;

import com.techelevator.model.Property;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcPropertyDao implements PropertyDao {
    private JdbcTemplate jdbcTemplate;

    public JdbcPropertyDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Property> listOfAllAvailableProperties() {
        List<Property> listedProperties = new ArrayList<>();
        String sql = "SELECT * FROM property WHERE is_available = true;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            listedProperties.add(mapRowToProperties(results));
        }
        return listedProperties;
    }
    @Override
    public Property getProperty(int propertyId) {
        Property property1 = null;
        String sql = "SELECT property_id, rental_price, address, is_available,landlord_id, due_date FROM property WHERE property_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, propertyId);
        if (results.next()) {
            property1 = (mapRowToProperties(results));
        }
        return property1;
    }
    @Override
    public Property createListing(Property property) {
        Property newProperty = null;
        String sql = "INSERT INTO property (rental_price, address, is_available, landlord_id, due_date) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING property_id;";
        int propertyId =
                jdbcTemplate.queryForObject(sql, Integer.class, property.getRentalPrice(),
                        property.getAddress(), property.isAvailable(), property.getLandlordId(), property.getDueDate());

        return getProperty(propertyId);

    }
    @Override
    public boolean updateProperty(Property property) {
        String sql = "UPDATE property SET rental_price = ?, address = ?, is_available = ?, landlord_id  = ?, due_date  = ?" +
                "WHERE property_id = ?;";
        return jdbcTemplate.update(sql, property.getRentalPrice(),
                property.getAddress(), property.isAvailable(), property.getLandlordId(), property.getDueDate()) == 1;
    }

    @Override
    public void deleteProperty(int propertyId) {
        String sql = "DELETE FROM property WHERE property_id = ?";
        jdbcTemplate.update(sql, propertyId);
    }

    private Property mapRowToProperties(SqlRowSet results) {
        Property property = new Property();
        property.setId(results.getInt("property_id"));
        property.setRentalPrice(results.getInt("rental_price"));
        property.setAvailable(results.getBoolean("is_available"));
        property.setAddress(results.getString("rental_address"));
        return property;
    }
}
