package com.techelevator.dao;

import com.techelevator.model.PropertyPhoto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPropertyPhotoDao implements PropertyPhotoDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPropertyPhotoDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public PropertyPhoto getPropertyPhoto(int photoId) {
        String sql = "SELECT photo_id, property_id, photo_url FROM property_photo WHERE photo_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, photoId);
        if (results.next()) {
            return mapRowToPropertyPhoto(results);
        }
        return null;
    }

    @Override
    public boolean updatePropertyPhoto(PropertyPhoto photo) {
        String sql = "UPDATE property_photo SET property_id = ?, photo_url = ? WHERE photo_id = ?";
        jdbcTemplate.update(sql, photo.getPropertyId(), photo.getPhotoUrl(), photo.getPhotoId());
        return false;
    }

    @Override
    public void addPropertyPhoto(PropertyPhoto photo) {
        String sql = "INSERT INTO property_photo (photo_id, property_id, photo_url) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, photo.getPhotoId(), photo.getPropertyId(), photo.getPhotoUrl());
    }

    @Override
    public void deletePropertyPhoto(int photoId) {
        String sql = "DELETE FROM property_photo WHERE photo_id = ?";
        jdbcTemplate.update(sql, photoId);
    }

    @Override
    public List<PropertyPhoto> getAllPropertyPhotos() {
        String sql = "SELECT photo_id, property_id, photo_url FROM property_photo";
        List<PropertyPhoto> propertyPhotos = new ArrayList<>();
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            propertyPhotos.add(mapRowToPropertyPhoto(results));
        }
        return propertyPhotos;
    }

    public PropertyPhoto mapRowToPropertyPhoto(SqlRowSet rs) {
        int photoId = rs.getInt("photo_id");
        int propertyId = rs.getInt("property_id");
        String photoUrl = rs.getString("photo_url");
        return new PropertyPhoto(photoId, propertyId, photoUrl);
    }

}
