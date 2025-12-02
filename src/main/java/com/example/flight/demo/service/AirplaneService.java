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

    public Airplane findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Airplane save(Airplane airplane) {
        return repository.save(airplane);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}