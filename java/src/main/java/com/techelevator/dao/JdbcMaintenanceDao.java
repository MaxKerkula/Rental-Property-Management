package com.techelevator.dao;

import com.techelevator.model.Maintenance;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMaintenanceDao implements MaintenanceDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcMaintenanceDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
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
    public Maintenance getRequestById(int id) {
        Maintenance request = null;
        String sql = "SELECT maintenance.*, maintenance_status.status_description " +
                "FROM maintenance " +
                "JOIN maintenance_status ON maintenance.status_id = maintenance_status.status_id " +
                "WHERE maintenance_request_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
        if (results.next()) {
            request = mapRowToMaintenance(results);
        }
        return request;
    }

    @Override
    public int createRequest(Maintenance newRequest, Principal principal) {
        String sql = "INSERT INTO maintenance (description, status_id, property_id, maintenance_worker_id) " +
                "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, newRequest.getDescription(), newRequest.getStatusId(),
                newRequest.getPropertyId(), newRequest.getMaintenanceWorkerId());
        return newRequest.getId();
    }

    @Override
    public boolean assignRequest(int requestId, int assignedTo, Principal principal) {
        String sql = "UPDATE maintenance " +
                "SET maintenance_worker_id = ? " +
                "WHERE maintenance_request_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, assignedTo, requestId);
        return rowsAffected == 1;
    }

    @Override
    public boolean updateRequestStatus(int requestId, int statusId, Principal principal) {
        String sql = "UPDATE maintenance " +
                "SET status_id = ? " +
                "WHERE maintenance_request_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, statusId, requestId);
        return rowsAffected == 1;
    }

    @Override
    public void deleteRequest(int id, Principal principal) {
        String sql = "DELETE FROM maintenance WHERE maintenance_request_id = ?";
        jdbcTemplate.update(sql, id);
    }

    private Maintenance mapRowToMaintenance(SqlRowSet rs) {
        Maintenance maintenance = new Maintenance();
        maintenance.setId(rs.getInt("maintenance_request_id"));
        maintenance.setDescription(rs.getString("description"));
        maintenance.setStatusId(rs.getInt("status_id"));
        maintenance.setPropertyId(rs.getInt("property_id"));
        maintenance.setMaintenanceWorkerId(rs.getInt("maintenance_worker_id"));
        return maintenance;
    }
}
