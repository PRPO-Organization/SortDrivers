package com.example.sortdrivers.dto;

public class SortRequest {
    private LocationDTO passengerLocation;
   
    
    private String sortBy; 

    public LocationDTO getPassengerLocation() { return passengerLocation; }
    public void setPassengerLocation(LocationDTO passengerLocation) { this.passengerLocation = passengerLocation; }

  

    public String getSortBy() { return sortBy; }
    public void setSortBy(String sortBy) { this.sortBy = sortBy; }
}
