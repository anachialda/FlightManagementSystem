package com.example.flight.demo.repository;

import com.example.flight.demo.model.Flight;
import org.springframework.stereotype.Repository;

@Repository
public class FlightRepository extends InFileRepository<Flight> {

    public FlightRepository() {
        super("flights.json", Flight.class);
    }
}
