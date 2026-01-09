package com.example.sortdrivers.dto;

public class LocationDTO {
    private double lat;
    private double lng;
    private Boolean isDriver ; 
    public double getLat() { return lat; }
    public void setLat(double lat) { this.lat = lat; }

    public double getLng() { return lng; }
    public void setLng(double lng) { this.lng = lng; }
    public Boolean getIsDriver(){
        return this.isDriver;
    }
    public void setIsDriver(Boolean isDriver){
        this.isDriver = isDriver ; 
    }
}
