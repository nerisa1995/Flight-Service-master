package com.Flight.service;

import com.Flight.entity.Flight;
import com.Flight.error.FlightNotFoundException;

import java.util.List;

public interface FlightService {


    public Flight saveFlight(Flight flight);

    public List<Flight> getFlightList();

    public Flight getFlightById(Long flightId) throws FlightNotFoundException;

    public void deleteFlightById(Long flightId);

    public Flight updateFlight(Long flightId, Flight flight);

    public Flight getFlightByName(String flightDestination);
}
