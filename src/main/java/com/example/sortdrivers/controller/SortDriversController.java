package com.example.sortdrivers.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sortdrivers.dto.SortRequest;
import com.example.sortdrivers.dto.UserLocationDTO;
import com.example.sortdrivers.service.SortDriversService;

@RestController
@RequestMapping("/api/sort-drivers")
public class SortDriversController {

    private final SortDriversService service;

    public SortDriversController(SortDriversService service) {
        this.service = service;
    }

    @PostMapping
    public List<UserLocationDTO> sort(@RequestBody SortRequest request) {
        return service.sortDrivers(request);
    }
}
