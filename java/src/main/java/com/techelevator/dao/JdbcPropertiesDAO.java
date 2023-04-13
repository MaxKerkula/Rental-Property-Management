package com.techelevator.dao;

import com.techelevator.model.Property;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcPropertiesDAO implements PropertiesDAO {
    private JdbcTemplate jdbcTemplate;

    public JdbcPropertiesDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Property> listOfAllAvailableProperties() {
        List<Property> listedProperties = new ArrayList<>();
        String sql = "SELECT * FROM properties WHERE is_available = true;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            listedProperties.add(mapRowToProperties(results));
        }
        return listedProperties;
    }


    @Override
    public boolean updateProperty(int propertyId) {
        String sql = "UPDATE properties SET property_id = ?, rental_price = ?, address = ?, is_available = ?" +
                "WHERE property_id = ?;";
        return jdbcTemplate.update(sql, propertyId) == 1;
    }

    @Override
    public Property getProperty(int propertyId) {
        Property property1 = null;
        String sql = "SELECT property_id, rental_price, address, is_available, FROM properties WHERE property_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, propertyId);
        if (results.next()) {
            property1 = (mapRowToProperties(results));
        }
        return property1;
    }


    @Override
    public Property createListing(Property listing) {

        String sql = "INSERT INTO properties (property_id, rental_price, address, is_available) " +
                "VALUES (?, ?, ?, ?) RETURNING property_id;";
        int propertyId =
                jdbcTemplate.queryForObject(sql, Integer.class, listing.getRentalPrice(),
                        listing.getAddress(), listing.isAvailable());
        listing.setId(propertyId);
        return getProperty(propertyId);
    }

    @Override
    public Property saveProperty(Property property) {
        Property newProperty = null;
        String sql = "INSERT INTO properties (property_id, rental_price, address, is_available) " +
                "VALUES (?, ?, ?, ?) RETURNING property_id;";
        int propertyId =
                jdbcTemplate.queryForObject(sql, Integer.class, property.getRentalPrice(),
                        property.getAddress(), property.isAvailable());

        return getProperty(propertyId);

    }

    @Override
    public void deleteProperty(int propertyId) {
        String sql = "DELETE FROM properties WHERE property_id = ?";
        jdbcTemplate.update(sql, propertyId);
    }

    private Property mapRowToProperties(SqlRowSet results) {
        Property properties = new Property();
        properties.setId(results.getInt("property_id"));
        properties.setRentalPrice(results.getInt("rental_price"));
        properties.setAvailable(results.getBoolean("is_available"));
        properties.setAddress(results.getString("rental_address"));
        return properties;
    }
}
