package com.example.sortdrivers.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.sortdrivers.dto.SortRequest;
import com.example.sortdrivers.dto.SortedDriverDTO;
import com.example.sortdrivers.dto.UserLocationDTO;
import com.example.sortdrivers.util.DistanceUtil;

@Service
public class SortDriversService {

    /**
     * Sorts drivers based on the sortBy field and optionally includes unavailable drivers at the end.
     *
     * @param request The sort request containing drivers, passenger location, sortBy, and includeUnavailable.
     * @return List of SortedDriverDTO sorted according to the rules.
     */
    private final String RATING_API = "https://9.235.137.130/api/ratings";
    private final String LOCATION_API = "https://9.235.137.130/api/location";


    @Autowired
    private RestTemplate restTemplate;
    public List<SortedDriverDTO> sortDrivers(SortRequest request) {
           double pLat = request.getPassengerLocation().getLat();
        double pLng = request.getPassengerLocation().getLng();

        // 1️⃣ Get all driver locations from API
        UserLocationDTO[] drivers = restTemplate.getForObject(LOCATION_API, UserLocationDTO[].class);

        // 2️⃣ Map to SortedDriverDTO
        List<SortedDriverDTO> driverDTOs = List.of(drivers).stream()
                .map(driver -> {
                    // Fetch rating for each driver
                    Double rating = getUserRating(driver.getId());

                    // Compute distance from passenger
                    double distanceKm = DistanceUtil.haversine(pLat, pLng, driver.getLat(), driver.getLng());

                    return new SortedDriverDTO(
                            (int) driver.getId(),        // cast long -> Integer
                            distanceKm,
                            rating != null ? rating : 0.0,
                            0.0,                        // ETA (can be computed later)
                            true                        // all are drivers
                    );
                })
                .collect(Collectors.toList());

        // 3️⃣ Determine sort mode
        String sortBy = request.getSortBy() != null ? request.getSortBy().toLowerCase() : "rating";

        Comparator<SortedDriverDTO> comparator;
        switch (sortBy) {
            case "distance":
                comparator = Comparator.comparingDouble(SortedDriverDTO::getDistanceKm)
                        .thenComparing(Comparator.comparingDouble(SortedDriverDTO::getRating).reversed());
                break;
            case "hybrid":
                comparator = Comparator
                        .comparingDouble(SortedDriverDTO::getDistanceKm)
                        .thenComparing(Comparator.comparingDouble(SortedDriverDTO::getRating).reversed());
                break;
            case "rating":
            default:
                comparator = Comparator.comparingDouble(SortedDriverDTO::getRating).reversed();
        }

        // 4️⃣ Sort and return
        return driverDTOs.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }

    

    public UserLocationDTO getUserLocation(Long userId) {
        return restTemplate.getForObject(RATING_API + "/nearest/" + userId,UserLocationDTO.class);
    }

    
    public Double getUserRating(Long userId) {
    // RATING_API is the base URL of the external ratings API, e.g., "https://external-api.com/ratings"
    String url = RATING_API + "/users/" + userId +"/avg";
    return restTemplate.getForObject(url, Double.class);
}
}
