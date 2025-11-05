package com.example.flight.demo.service;

import com.example.flight.demo.model.Flight;
import com.example.flight.demo.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FlightService {
    private final FlightRepository flights;

    public FlightService(FlightRepository flights) {
        this.flights = flights;
    }

    public List<Flight> all() { return flights.findAll(); }

    public Flight save(Flight f) {
        return flights.save(f);
    }

}