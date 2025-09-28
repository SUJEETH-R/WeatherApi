package com.example.demo.service;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ForecastResponseDTO;
import com.example.demo.dto.WeatherResponseDTO;


@Service
public class WeatherServiceImpl implements WeatherService {
	@Override
    @Cacheable(value = "weatherCache", key = "#location")
    public WeatherResponseDTO getCurrentWeather(String location) {
        WeatherResponseDTO dto = new WeatherResponseDTO();
        dto.setLocation(location);
        dto.setTemperature("28°C");
        dto.setCondition("Sunny");
        return dto;
    }

    @Override
    @Cacheable(value = "weatherCache", key = "#location + '-' + #days")
    public ForecastResponseDTO getForecast(String location, int days) {
        ForecastResponseDTO forecast = new ForecastResponseDTO();
        forecast.setLocation(location);
        forecast.setForecast(
            java.util.stream.IntStream.range(0, days)
                .mapToObj(i -> {
                    WeatherResponseDTO dto = new WeatherResponseDTO();
                    dto.setLocation(location);
                    dto.setTemperature(25 + i + "°C");
                    dto.setCondition("Sunny");
                    return dto;
                }).toList()
        );
        return forecast;
    }
    private final List<String> availableLocations = List.of(
            "London", "New York", "Tokyo", "Paris", "Mumbai", "Delhi"
        );

        @Override
        public List<String> searchLocations(String query) {
            return availableLocations.stream()
                .filter(loc -> loc.toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
        }
}
