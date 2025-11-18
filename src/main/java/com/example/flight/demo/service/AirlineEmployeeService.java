package com.example.flight.demo.service;

import com.example.flight.demo.model.AirlineEmployee;

import com.example.flight.demo.repository.AirlineEmployeeRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AirlineEmployeeService {

    private final AirlineEmployeeRepository repository;

    public AirlineEmployeeService(AirlineEmployeeRepository repository) {

        this.repository = repository;

    }

    public List<AirlineEmployee> findAll() {

        return repository.findAll();

    }

    public AirlineEmployee findById(String id) {

        return repository.findById(id);

    }

    public void save(AirlineEmployee employee) {

        repository.save(employee);

    }

    public void delete(String id) {

        repository.deleteById(id);

    }

}
 