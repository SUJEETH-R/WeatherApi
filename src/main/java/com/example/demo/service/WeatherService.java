package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ForecastResponseDTO;
import com.example.demo.dto.WeatherResponseDTO;

public interface WeatherService {
	
	  WeatherResponseDTO getCurrentWeather(String location);
	    ForecastResponseDTO getForecast(String location, int days);
	    List<String> searchLocations(String query);
}
