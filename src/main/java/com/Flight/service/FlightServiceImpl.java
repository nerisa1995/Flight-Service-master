package com.Flight.service;

import com.Flight.entity.Flight;
import com.Flight.error.FlightNotFoundException;
import com.Flight.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Flight saveFlight(@Valid Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public List<Flight> getFlightList() {
        return flightRepository.findAll();
    }

    @Override
    public Flight getFlightById(Long flightId) throws FlightNotFoundException {
        Optional<Flight> flight =
                flightRepository.findById(flightId);
        if (!flight.isPresent()) {
            throw new FlightNotFoundException("Flight Not Avaiable");
        }
        return flight.get();
    }

    @Override
    public void deleteFlightById(Long flightId) {
        flightRepository.deleteById(flightId);
    }

    @Override
    public Flight updateFlight(Long flightId, Flight flight) {
        Flight flightDB = flightRepository.findById(flightId).get();
        if (Objects.nonNull(flight.getFlightDestination()) &&
                !"".equalsIgnoreCase(flight.getFlightDestination())) {
            flightDB.setFlightDestination(flight.getFlightDestination());
        }
        if (Objects.nonNull(flight.getFlightDeparture()) &&
                !"".equalsIgnoreCase(flight.getFlightDeparture())) {
            flightDB.setFlightDeparture(flight.getFlightDeparture());
        }

        if (Objects.nonNull(flight.getFlightCode()) &&
                !"".equalsIgnoreCase(flight.getFlightCode())) {
            flightDB.setFlightCode(flight.getFlightCode());
        }

        return flightRepository.save(flightDB);
    }

    @Override
    public Flight getFlightByName(String flightDestination) {
        return flightRepository.findByFlightDestinationIgnoreCase(flightDestination);

    }
}
