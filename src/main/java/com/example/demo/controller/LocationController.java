package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.WeatherService;
@RestController
@RequestMapping("/locations")
public class LocationController {


    private WeatherService weatherService;

	public LocationController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/search")
    public List<String> searchLocations(@RequestParam String q) {
        return weatherService.searchLocations(q);
    }
}
