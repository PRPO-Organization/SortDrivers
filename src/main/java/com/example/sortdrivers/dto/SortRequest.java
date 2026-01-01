package com.example.sortdrivers.dto;

import java.util.List;

public class SortRequest {
    private LocationDTO passengerLocation;
    private List<DriverDTO> drivers;
    
    private String sortBy; 

    public LocationDTO getPassengerLocation() { return passengerLocation; }
    public void setPassengerLocation(LocationDTO passengerLocation) { this.passengerLocation = passengerLocation; }

    public List<DriverDTO> getDrivers() { return drivers; }
    public void setDrivers(List<DriverDTO> drivers) { this.drivers = drivers; }

    public String getSortBy() { return sortBy; }
    public void setSortBy(String sortBy) { this.sortBy = sortBy; }
}
