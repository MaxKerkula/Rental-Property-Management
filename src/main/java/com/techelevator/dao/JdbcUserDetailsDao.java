package com.techelevator.dao;

import com.techelevator.model.UserDetails;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcUserDetailsDao implements UserDetailsDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcUserDetailsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserDetails getLoggedInUserDetails(int id) {
        String sql = "SELECT * FROM user_details WHERE user_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        if (result.next()) {
            return mapRowToUserDetails(result);
        }
        return null;
    }

    @Override
    public UserDetails createUserDetails(int userId, UserDetails userDetails) {
        String sql = "INSERT INTO user_details (user_id, property_id, first_name, last_name, email, phone, has_pets) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING tenant_id";
        int tenantId = jdbcTemplate.queryForObject(sql, Integer.class, userId, userDetails.getPropertyId(), userDetails.getFirstName(), userDetails.getLastName(), userDetails.getEmail(), userDetails.getPhone(), userDetails.getHasPets());
        return getLoggedInUserDetails(userId);
    }


    @Override
    public boolean updateUserDetails(int id, UserDetails userDetails) {
        String sql = "UPDATE user_details SET user_id = ?, property_id = ?, first_name = ?, last_name = ?, email = ?, phone = ?, has_pets = ? WHERE tenant_id = ?";
        jdbcTemplate.update(sql, userDetails.getUserId(), userDetails.getPropertyId(), userDetails.getFirstName(), userDetails.getLastName(), userDetails.getEmail(), userDetails.getPhone(), userDetails.getHasPets(), id);
        return true;
    }

    @Override
    public void deleteUserDetails(int id) {
        String sql = "DELETE FROM user_details WHERE tenant_id = ?";
        jdbcTemplate.update(sql, id);
    }

    private UserDetails mapRowToUserDetails(SqlRowSet row) {
        UserDetails userDetails = new UserDetails();
        userDetails.setTenantId(row.getInt("tenant_id"));
        userDetails.setUserId(row.getInt("user_id"));
        userDetails.setPropertyId(row.getInt("property_id"));
        userDetails.setFirstName(row.getString("first_name"));
        userDetails.setLastName(row.getString("last_name"));
        userDetails.setEmail(row.getString("email"));
        userDetails.setPhone(row.getString("phone"));
        userDetails.setHasPets(row.getBoolean("has_pets"));
        return userDetails;
    }
}
