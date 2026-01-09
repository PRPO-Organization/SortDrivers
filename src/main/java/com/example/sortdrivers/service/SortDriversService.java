package com.example.sortdrivers.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.sortdrivers.dto.LocationDTO;
import com.example.sortdrivers.dto.SortRequest;
import com.example.sortdrivers.dto.UserLocationDTO;

@Service
public class SortDriversService {
   private static final Logger log = LoggerFactory.getLogger(SortDriversService.class);
    /**
     * Sorts drivers based on the sortBy field and optionally includes unavailable drivers at the end.
     *
     * @param request The sort request containing drivers, passenger location, sortBy, and includeUnavailable.
     * @return List of SortedDriverDTO sorted according to the rules.
     */
    private final String RATING_API = "http://9.235.137.130/api/ratings";
    private final String LOCATION_API = "http://9.235.137.130/api/location";


    @Autowired
    private RestTemplate restTemplate;
    public List<UserLocationDTO> sortDrivers(SortRequest request) {
           double pLat = request.getPassengerLocation().getLat();
        double pLng = request.getPassengerLocation().getLng();
        try{
                List<UserLocationDTO> drivers = this.getUserLocation(request.getPassengerId(),request.getPassengerLocation());
                return drivers;
        }catch(Exception e){
                return null;
        }
        
    }

    

   

  

public List<UserLocationDTO> getUserLocation(Long userId, LocationDTO location) {
    String url =  LOCATION_API+ "/nearest/" + userId;
        log.info("Calling location API: {} with location: lat={}, lng={}", 
                 url, location.getLat(), location.getLng());
    ResponseEntity<List<UserLocationDTO>> response = restTemplate.exchange(
        url,
        HttpMethod.POST,
        new HttpEntity<>(location),
        new ParameterizedTypeReference<List<UserLocationDTO>>() {}
    );

    return response.getBody();
}
    public Double getUserRating(Long userId) {
    String url = RATING_API + "/users/" + userId +"/avg";
    System.out.println(url);
    return restTemplate.getForObject(url, Double.class);
}
}
