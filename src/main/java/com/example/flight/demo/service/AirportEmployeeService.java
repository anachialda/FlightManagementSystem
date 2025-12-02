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

    public AirportEmployee findById(Long id) {
        return repository.findById(id)
                .orElse(null);
    }

    public AirportEmployee save(AirportEmployee employee) {
        return repository.save(employee);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}