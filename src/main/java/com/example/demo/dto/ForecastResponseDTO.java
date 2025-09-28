package com.example.demo.dto;

import java.util.List;

public class ForecastResponseDTO {

	private String location;
	private List<WeatherResponseDTO> forecast;
	
	public ForecastResponseDTO() {
		// TODO Auto-generated constructor stub
	}

	public ForecastResponseDTO(String location, List<WeatherResponseDTO> forecast) {
		super();
		this.location = location;
		this.forecast = forecast;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<WeatherResponseDTO> getForecast() {
		return forecast;
	}

	public void setForecast(List<WeatherResponseDTO> forecast) {
		this.forecast = forecast;
	}

}
