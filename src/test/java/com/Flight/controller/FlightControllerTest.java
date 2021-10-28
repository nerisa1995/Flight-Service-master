package com.Flight.controller;

import com.Flight.entity.Flight;
import com.Flight.service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FlightController.class)
class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlightService flightService;

    private Flight flight;

    @BeforeEach
    void setUp() {
        flight = Flight.builder()
                .flightDestination("Tirana")
                .flightCode("IT-06")
                .flightDeparture("IT")
                .flightId(1L)
                .build();
    }

    @Test
    void saveFlight() throws Exception {
        Flight inputFlight = Flight.builder()
                .flightDestination("Tirana")
                .flightCode("IT-06")
                .flightDeparture("IT")
                .build();

        Mockito.when(flightService.saveFlight(inputFlight))
                .thenReturn(flight);

        mockMvc.perform(post("/flights")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"flightDestination\":\"Tirana\",\n" +
                                "\t\"flightCode\":\"IT-06\",\n" +
                                "\t\"flightDeparture\":\"IT\"\n" +
                                "}"))
                .andExpect(status().isOk());

    }

    @Test
    void getFlightById() throws Exception {
        Mockito.when(flightService.getFlightById(1L))
                .thenReturn(flight);
        // here we perform the get operation
        mockMvc.perform(get("/flights/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flightDestination").value(flight.getFlightDestination()));


    }



}