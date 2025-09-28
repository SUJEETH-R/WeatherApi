package com.example.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controller.LocationController;
import com.example.demo.service.WeatherService;

@WebMvcTest(LocationController.class)
public class LocationControllerTest {

	  @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private WeatherService weatherService;

	    @Test
	    public void testSearchLocations() throws Exception {
	        // Mock the service response
	        when(weatherService.searchLocations("lo")).thenReturn(List.of("London"));

	        // Perform GET request and validate
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
}
