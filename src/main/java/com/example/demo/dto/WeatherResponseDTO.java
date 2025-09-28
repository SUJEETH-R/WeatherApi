package com.example.demo.dto;

public class WeatherResponseDTO {
	private String location;
	private String temperature;
	private String condition;
    public WeatherResponseDTO() { }

	public WeatherResponseDTO(String location, String temperature, String condition) {

		this.location = location;
		this.temperature = temperature;
		this.condition = condition;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

}
