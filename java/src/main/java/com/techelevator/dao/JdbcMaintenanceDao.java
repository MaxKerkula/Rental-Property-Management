package com.techelevator.dao;

import com.techelevator.model.Maintenance;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMaintenanceDao implements MaintenanceDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcMaintenanceDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Maintenance> getAllRequests() {
        List<Maintenance> requests = new ArrayList<>();
        String sql = "SELECT * FROM maintenance";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Maintenance request = mapRowToMaintenance(results);
            requests.add(request);
        }

        return requests;
    }

    @Override
    public Maintenance getRequestById(int requestId) {
        String sql = "SELECT * FROM maintenance WHERE maintenance_request_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, requestId);
        if (result.next()) {
            return mapRowToMaintenance(result);
        } else {
            return null;
        }
    }

    @Override
    public Maintenance createRequest(Maintenance request) {
        String sql = "INSERT INTO maintenance (description, status_id, property_id, maintenance_worker_id) VALUES (?, ?, ?, ?) RETURNING maintenance_request_id";
        int requestId = jdbcTemplate.queryForObject(sql, Integer.class, request.getDescription(), request.getStatusId(), request.getPropertyId(), request.getMaintenanceWorkerId());
        request.setId(requestId);
        return request;
    }

    @Override
    public boolean assignRequest(int requestId, int assignedTo) {
        String sql = "UPDATE maintenance SET maintenance_worker_id = ? WHERE maintenance_request_id = ?";
        return jdbcTemplate.update(sql, assignedTo, requestId) == 1;
    }

    @Override
    public boolean updateRequestStatus(int requestId, int statusId) {
        String sql = "UPDATE maintenance SET status_id = ? WHERE maintenance_request_id = ?";
        return jdbcTemplate.update(sql, statusId, requestId) == 1;
    }

    @Override
    public void deleteRequest(int id) {
        String sql = "DELETE FROM maintenance WHERE maintenance_request_id = ?";
        jdbcTemplate.update(sql, id);
    }

    private Maintenance mapRowToMaintenance(SqlRowSet row) {
        Maintenance request = new Maintenance();
        request.setId(row.getInt("maintenance_request_id"));
        request.setDescription(row.getString("description"));
        request.setStatusId(row.getInt("status_id"));
        request.setPropertyId(row.getInt("property_id"));
        request.setMaintenanceWorkerId(row.getInt("maintenance_worker_id"));
        return request;
    }
}
