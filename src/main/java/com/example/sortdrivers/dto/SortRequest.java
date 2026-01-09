package com.example.sortdrivers.dto;

public class SortRequest {
    private LocationDTO passengerLocation;
    private Long passengerId ; 
    public SortRequest(){}
    
    private String sortBy; 

    public LocationDTO getPassengerLocation() { return passengerLocation; }
    public void setPassengerLocation(LocationDTO passengerLocation) { this.passengerLocation = passengerLocation; }

  

    public String getSortBy() { return sortBy; }
    public void setSortBy(String sortBy) { this.sortBy = sortBy; }
    public Long getPassengerId (){
        return this.passengerId;
    }
    public void setPassengerId(Long id){
        this.passengerId= id;
    }
}
