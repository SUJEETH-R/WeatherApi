package com.example.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.dto.ForecastResponseDTO;
import com.example.demo.dto.WeatherResponseDTO;
import com.example.demo.service.WeatherService;

@SpringBootTest
@MockBean
class WeatherApiApplicationTests {

	@Test
	void contextLoads() {
	}
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    // ---------------------- Current Weather ----------------------
    @Test
    public void testGetCurrentWeather() throws Exception {
        WeatherResponseDTO dto = new WeatherResponseDTO();
        dto.setLocation("London");
        dto.setTemperature("28°C");
        dto.setCondition("Sunny");

        when(weatherService.getCurrentWeather("London")).thenReturn(dto);

        mockMvc.perform(get("/weather/current")
                        .param("location", "London"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.location").value("London"))
                .andExpect(jsonPath("$.temperature").value("28°C"))
                .andExpect(jsonPath("$.condition").value("Sunny"));
    }

    // ---------------------- Forecast ----------------------
    @Test
    public void testGetForecast() throws Exception {
        ForecastResponseDTO forecast = new ForecastResponseDTO();
        forecast.setLocation("London");

        List<WeatherResponseDTO> forecastList = List.of(
                new WeatherResponseDTO() {{ setLocation("London"); setTemperature("25°C"); setCondition("Sunny"); }},
                new WeatherResponseDTO() {{ setLocation("London"); setTemperature("26°C"); setCondition("Sunny"); }}
        );
        forecast.setForecast(forecastList);

        when(weatherService.getForecast("London", 2)).thenReturn(forecast);

        mockMvc.perform(get("/weather/forecast")
                        .param("location", "London")
                        .param("days", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.location").value("London"))
                .andExpect(jsonPath("$.forecast.length()").value(2))
                .andExpect(jsonPath("$.forecast[0].temperature").value("25°C"));
    }

    // ---------------------- Locations Search ----------------------
    @Test
    public void testSearchLocations() throws Exception {
        when(weatherService.searchLocations("lo")).thenReturn(List.of("London"));

        mockMvc.perform(get("/locations/search")
                        .param("q", "lo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("London"));
    }

    @Test
    public void testSearchLocationsMultipleResults() throws Exception {
        when(weatherService.searchLocations("o")).thenReturn(List.of("London", "Tokyo", "New York"));

        mockMvc.perform(get("/locations/search")
                        .param("q", "o"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(3))
                .andExpect(jsonPath("$[1]").value("Tokyo"));
    }

    @Test
    public void testSearchLocationsEmptyResult() throws Exception {
        when(weatherService.searchLocations("xyz")).thenReturn(List.of());

        mockMvc.perform(get("/locations/search")
                        .param("q", "xyz"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    // ---------------------- Health Check ----------------------
    @Test
    public void testHealthCheck() throws Exception {
        mockMvc.perform(get("/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("Weather API is running!"));
    }

}
