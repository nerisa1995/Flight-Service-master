package com.Flight.controller;

import com.Flight.entity.Flight;
import com.Flight.error.FlightNotFoundException;
import com.Flight.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class FlightController {

    @Autowired
    private FlightService flightService;
    private final Logger LOGGER =
            LoggerFactory.getLogger(FlightController.class);

    @PostMapping("/flights")
    public Flight saveFlight(@Valid @RequestBody Flight flight) {
        LOGGER.info("Inside saveFlight of FlightController");
        return flightService.saveFlight(flight);
    }

    @GetMapping("/flights")
    public List<Flight> getFlightList() {
        LOGGER.info("Inside getFlightList of FlightController");
        return flightService.getFlightList();
    }

    @GetMapping("/flights/{id}")
    public Flight getFlightById(@PathVariable("id") Long flightId) throws FlightNotFoundException {

        return flightService.getFlightById(flightId);
    }

    @DeleteMapping("/flights/{id}")
    public String deleteFlightById(@PathVariable("id") Long flightId) {

        flightService.deleteFlightById(flightId);
        return "Deleted flightId successfully " + flightId;
    }

    @PutMapping("/flights/{id}")
    public Flight updateFlight(@PathVariable("id") Long flightId,
                                   @RequestBody Flight flight) {
        return flightService.updateFlight(flightId, flight);
    }

    //Fetch data by name
    @GetMapping("/flights/flightDestination/{flightDestination}")
    public Flight getFlightByName(@PathVariable("flightDestination") String flightDestination) {
        return flightService.getFlightByName(flightDestination);

    }

}
