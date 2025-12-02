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

    public Flight findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Flight save(Flight flight) {
        return repository.save(flight);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}