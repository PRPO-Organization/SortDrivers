package com.example.sortdrivers.dto;

public class SortedDriverDTO {
    private Integer driverId;
    private double distanceKm;
    private Double rating;
    private Double eta;
    private Boolean available;

    public SortedDriverDTO(Integer driverId, double distanceKm, Double rating, double eta, Boolean available) {
        this.driverId = driverId;
        this.distanceKm = distanceKm;
        this.rating = rating;
        this.eta = eta;
        this.available = available;
    }

    public Integer getDriverId() { return driverId; }
    public double getDistanceKm() { return distanceKm; }
    public double getRating() { return rating; }
    public double getEta() { return eta; }
    public boolean isAvailable(){ return available; }

}
