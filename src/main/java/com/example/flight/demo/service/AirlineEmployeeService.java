package com.example.flight.demo.service;

import com.example.flight.demo.model.AirlineEmployee;
import com.example.flight.demo.repository.AirlineEmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirlineEmployeeService {
    private final AirlineEmployeeRepository employees;

    public AirlineEmployeeService(AirlineEmployeeRepository employees) {
        this.employees = employees;
    }

    public AirlineEmployee save(AirlineEmployee e) { return employees.save(e); }
    public boolean delete(Long id) { return employees.delete(id); }
    public Optional<AirlineEmployee> find(Long id) { return employees.findById(id); }
    public List<AirlineEmployee> all() { return employees.findAll(); }

    //public List<AirlineEmployee> byRole(String role) { return employees.findByRole(role);}
}