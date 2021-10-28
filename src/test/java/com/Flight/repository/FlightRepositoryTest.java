package com.Flight.repository;
import com.Flight.entity.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class FlightRepositoryTest {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private TestEntityManager entityManager;
    @BeforeEach
    void setUp() {
        Flight department =
                Flight.builder()
                        .flightDestination("Tirana")
                        .flightCode("ME-011")
                        .flightDeparture("Milano")
                        .build();

        entityManager.persist(department);
    }

    @Test
    public void whenFindById_thenReturnDepartment(){

        Flight flight = flightRepository.findById(1L).get();
        assertEquals(flight.getFlightDestination(), "Tirana");
    }
}