//package com.techelevator.service;
//
//import com.techelevator.dao.PropertyPhotoDao;
//import com.techelevator.model.PropertyPhoto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class PropertyPhotoServiceImp implements PropertyPhotoService {
//    private final PropertyPhotoDao propertyPhotoDao;
//
//    @Autowired
//    public PropertyPhotoServiceImp(PropertyPhotoDao propertyPhotoDao) {
//        this.propertyPhotoDao = propertyPhotoDao;
//    }
//
//    public PropertyPhoto getPropertyPhoto(int photoId) {
//        return propertyPhotoDao.getPropertyPhoto(photoId);
//    }
//
//    public void updatePropertyPhoto(PropertyPhoto photo) {
//        propertyPhotoDao.updatePropertyPhoto(photo);
//    }
//
//    public void addPropertyPhoto(PropertyPhoto photo) {
//        propertyPhotoDao.addPropertyPhoto(photo);
//    }
//
//    public void deletePropertyPhoto(int photoId) {
//        propertyPhotoDao.deletePropertyPhoto(photoId);
//    }
//
//    public List<PropertyPhoto> getAllPropertyPhotos() {
//        return propertyPhotoDao.getAllPropertyPhotos();
//    }
//}
