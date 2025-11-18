package com.example.flight.demo.service;

import com.example.flight.demo.model.Flight;
import com.example.flight.demo.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    private final FlightRepository repository;

    public FlightService(FlightRepository repository) {
        this.repository = repository;
    }

    public List<Flight> findAll() {
        return repository.findAll();
    }

    public Flight findById(String id) {
        return repository.findById(id);
    }

    public void save(Flight flight) {
        repository.save(flight);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}