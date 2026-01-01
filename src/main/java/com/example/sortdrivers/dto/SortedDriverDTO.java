package com.example.sortdrivers.dto;

public class SortedDriverDTO {
    private String driverId;
    private double distanceKm;
    private double rating;
    private double eta;
    private boolean available;

    public SortedDriverDTO(String driverId, double distanceKm, double rating, double eta, boolean available) {
        this.driverId = driverId;
        this.distanceKm = distanceKm;
        this.rating = rating;
        this.eta = eta;
        this.available = available;
    }

    public String getDriverId() { return driverId; }
    public double getDistanceKm() { return distanceKm; }
    public double getRating() { return rating; }
    public double getEta() { return eta; }
    public boolean isAvailable(){ return available; }

}
