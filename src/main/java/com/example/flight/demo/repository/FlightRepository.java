package com.example.flight.demo.repository;

import com.example.flight.demo.model.Flight;
import org.springframework.stereotype.Repository;

@Repository
public class FlightRepository extends InMemoryRepository<Flight> {
    public FlightRepository() {
        super(Flight::getId, Flight::setId);
    }
}
