package com.techelevator.model;

public class PropertyPhoto {
    private int photoId;
    private int propertyId;
    private String photoUrl;

    public PropertyPhoto(int photoId, int propertyId, String photoUrl) {
        this.photoId = photoId;
        this.propertyId = propertyId;
        this.photoUrl = photoUrl;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
