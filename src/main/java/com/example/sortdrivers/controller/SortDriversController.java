package com.example.sortdrivers.controller;

import com.example.sortdrivers.dto.SortRequest;
import com.example.sortdrivers.dto.SortedDriverDTO;
import com.example.sortdrivers.service.SortDriversService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sort-drivers")
public class SortDriversController {

    private final SortDriversService service;

    public SortDriversController(SortDriversService service) {
        this.service = service;
    }

    @PostMapping
    public List<SortedDriverDTO> sort(@RequestBody SortRequest request) {
        return service.sortDrivers(request);
    }
}
