package com.example.sortdrivers.dto;

public class DriverDTO {
    private String driverId;
    private double lat;
    private double lng;
    private double rating;
    private boolean available;
    private double eta;

    public String getDriverId() { return driverId; }
    public void setDriverId(String driverId) { this.driverId = driverId; }

    public double getLat() { return lat; }
    public void setLat(double lat) { this.lat = lat; }

    public double getLng() { return lng; }
    public void setLng(double lng) { this.lng = lng; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public double getEta() { return eta; }
    public void setEta(double eta) { this.eta = eta; }
}
