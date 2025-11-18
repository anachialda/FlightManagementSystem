package com.example.flight.demo.service;

import com.example.flight.demo.model.Airplane;
import com.example.flight.demo.repository.AirplaneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirplaneService {

    private final AirplaneRepository repository;

    public AirplaneService(AirplaneRepository repository) {
        this.repository = repository;
    }

    public List<Airplane> findAll() {
        return repository.findAll();
    }

    public Airplane findById(String id) {
        return repository.findById(id);
    }

    public void save(Airplane airplane) {
        repository.save(airplane);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}