package com.Flight.service;

import com.Flight.entity.Flight;
import com.Flight.repository.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FlightServiceTest {

    @Autowired
    private FlightService flightService;

    @MockBean
    private FlightRepository flightRepository;
    @BeforeEach
    void setUp() {
        Flight flight =
                Flight.builder()
                        .flightDestination("IT")
                        .flightDeparture("Tirana")
                        .flightCode("Tirana-01")
                        .flightId(1L)
                        .build();
        Mockito.when(flightRepository.findByFlightDestinationIgnoreCase("IT"))
                .thenReturn(flight);
    }

    @Test
    @DisplayName("Get Data based on Valid Flight Destination")
    public void whenValidFlightDestination_thenFlightShouldFound(){
        String flightDestination = "IT";
        Flight found = flightService.getFlightByName(flightDestination);
        assertEquals(flightDestination, found.getFlightDestination());


    }
}