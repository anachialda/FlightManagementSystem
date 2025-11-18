package com.example.flight.demo.service;

import com.example.flight.demo.model.AirportEmployee;
import com.example.flight.demo.repository.AirportEmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportEmployeeService {

    private final AirportEmployeeRepository repository;

    public AirportEmployeeService(AirportEmployeeRepository repository) {
        this.repository = repository;
    }

    public List<AirportEmployee> findAll() {
        return repository.findAll();
    }

    public AirportEmployee findById(String id) {
        return repository.findById(id);
    }

    public void save(AirportEmployee employee) {
        repository.save(employee);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}