package com.example.flight.demo.service;

import com.example.flight.demo.model.Passenger;
import com.example.flight.demo.repository.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {
    private final PassengerRepository passengers;

    public PassengerService(PassengerRepository passengers) {
        this.passengers = passengers;
    }

    public Passenger save(Passenger p) { return passengers.save(p); }
    public boolean delete(Long id) { return passengers.delete(id); }
    public Optional<Passenger> find(Long id) { return passengers.findById(id); }
    public List<Passenger> all() { return passengers.findAll(); }

    // helper
    public List<Passenger> byCurrency(String currency) { return passengers.findByCurrency(currency);}
}