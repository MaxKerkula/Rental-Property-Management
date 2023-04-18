package com.techelevator.dao;

import com.techelevator.model.PropertyPhoto;

import java.util.List;

public interface PropertyPhotoDao {
    PropertyPhoto getPropertyPhoto(int photoId);

    boolean updatePropertyPhoto(PropertyPhoto photo);

    void addPropertyPhoto(PropertyPhoto photo);

    void deletePropertyPhoto(int photoId);

    List<PropertyPhoto> getAllPropertyPhotos();
}