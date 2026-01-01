package com.example.sortdrivers.service;

import com.example.sortdrivers.dto.*;
import com.example.sortdrivers.util.DistanceUtil;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SortDriversService {

    /**
     * Sorts drivers based on the sortBy field and optionally includes unavailable drivers at the end.
     *
     * @param request The sort request containing drivers, passenger location, sortBy, and includeUnavailable.
     * @return List of SortedDriverDTO sorted according to the rules.
     */
    public List<SortedDriverDTO> sortDrivers(SortRequest request) {
        double pLat = request.getPassengerLocation().getLat();
        double pLng = request.getPassengerLocation().getLng();

        // Map all drivers to SortedDriverDTO (including unavailable if needed)
        List<SortedDriverDTO> drivers = request.getDrivers().stream()
                .map(d -> new SortedDriverDTO(
                        d.getDriverId(),
                        DistanceUtil.haversine(pLat, pLng, d.getLat(), d.getLng()),
                        d.getRating(),
                        d.getEta(),
                        d.isAvailable()
                ))
                .collect(Collectors.toList());

        // Default sorting mode
        String sortBy = request.getSortBy();
        if (sortBy == null) sortBy = "distance";

        // Comparator for the chosen sorting mode
        Comparator<SortedDriverDTO> comparator;

        switch (sortBy.toLowerCase()) {
            case "rating":
                comparator = Comparator.comparingDouble(SortedDriverDTO::getRating).reversed();
                break;
            case "eta":
                comparator = Comparator.comparingDouble(SortedDriverDTO::getEta);
                break;
            case "hybrid":
                comparator = Comparator
                        .comparingDouble(SortedDriverDTO::getEta)
                        .thenComparingDouble(SortedDriverDTO::getDistanceKm)
                        .thenComparing(Comparator.comparingDouble(SortedDriverDTO::getRating).reversed());
                break;
            default: // "distance"
                comparator = Comparator
                        .comparingDouble(SortedDriverDTO::getDistanceKm)
                        .thenComparing(Comparator.comparingDouble(SortedDriverDTO::getRating).reversed());
                break;
        }

        // Add availability as top-level sorting: available drivers first
        comparator = Comparator.comparing(SortedDriverDTO::isAvailable).reversed()
                .thenComparing(comparator);

        return drivers.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }
}
