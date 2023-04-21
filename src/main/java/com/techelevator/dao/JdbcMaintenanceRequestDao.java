package com.techelevator.dao;

import com.techelevator.model.MaintenanceRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMaintenanceRequestDao implements MaintenanceRequestDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcMaintenanceRequestDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<MaintenanceRequest> getAllRequestsById(int userId) {
        List<MaintenanceRequest> requests = new ArrayList<>();
        String sql = "SELECT * FROM maintenance_request WHERE user_id = ?";
        SqlRowSet results = this.jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            MaintenanceRequest request = this.mapRowToMaintenance(results);
            requests.add(request);
        }
        return requests;
    }

    public List<MaintenanceRequest> getAllRequests() {
        List<MaintenanceRequest> requests = new ArrayList<>();
        String sql = "SELECT * FROM maintenance_request";
        SqlRowSet results = this.jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            MaintenanceRequest request = this.mapRowToMaintenance(results);
            requests.add(request);
        }

        return requests;
    }

    public MaintenanceRequest getRequestById(int id) {
        String sql = "SELECT maintenance_request.*, maintenance_status.status_description FROM maintenance_request JOIN maintenance_status ON maintenance_request.status_id = maintenance_status.status_id WHERE maintenance_request_id = ?";

        SqlRowSet rowSet = this.jdbcTemplate.queryForRowSet(sql, id);

        return rowSet.next() ? this.mapRowToMaintenance(rowSet) : null;
    }

    public MaintenanceRequest createRequest(MaintenanceRequest newRequest) {
        String sql = "INSERT INTO maintenance_request (description, status_id, property_id, maintenance_worker_id) VALUES (?, ?, ?, ?) RETURNING maintenance_request_id";

        int requestId = jdbcTemplate.queryForObject(sql, Integer.class, newRequest.getDescription(), newRequest.getStatusId(), newRequest.getPropertyId(), newRequest.getMaintenanceWorkerId());

        newRequest.setMaintenanceRequestId(requestId);

        return newRequest;
    }


    public boolean updateMaintenanceRequest(int requestId, MaintenanceRequest updatedRequest) {
        String sql = "UPDATE maintenance_request SET maintenance_worker_id = ?, status_id = ?, description = ?, property_id = ? WHERE maintenance_request_id = ?";

        return jdbcTemplate.update(sql, updatedRequest.getMaintenanceWorkerId(), updatedRequest.getStatusId(), updatedRequest.getDescription(), updatedRequest.getPropertyId(), requestId) == 1;
    }

    public boolean updateMaintenanceRequestStatus(int requestId, int statusId) {
        String sql = "UPDATE maintenance_request SET status_id = ? WHERE maintenance_request_id = ?";
        return jdbcTemplate.update(sql, statusId, requestId) == 1;
    }

    public void deleteRequest(int id) {
        String sql = "DELETE FROM maintenanceRequest WHERE maintenance_request_id = ?";
        this.jdbcTemplate.update(sql, getRequestById(id));
    }

    private MaintenanceRequest mapRowToMaintenance(SqlRowSet rs) {
        MaintenanceRequest maintenanceRequest = new MaintenanceRequest();
        maintenanceRequest.setMaintenanceRequestId(rs.getInt("maintenance_request_id"));
        maintenanceRequest.setDescription(rs.getString("description"));
        maintenanceRequest.setStatusId(rs.getInt("status_id"));
        maintenanceRequest.setPropertyId(rs.getInt("property_id"));
        maintenanceRequest.setMaintenanceWorkerId(rs.getInt("maintenance_worker_id"));
        return maintenanceRequest;
    }
}