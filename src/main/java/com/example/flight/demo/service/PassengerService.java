package com.example.flight.demo.service;

import com.example.flight.demo.model.Passenger;
import com.example.flight.demo.repository.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {

    private final PassengerRepository repository;

    public PassengerService(PassengerRepository repository) {
        this.repository = repository;
    }

    public List<Passenger> findAll() {
        return repository.findAll();
    }

    public Passenger findById(String id) {
        return repository.findById(id);
    }

    public void save(Passenger passenger) {
        repository.save(passenger);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}