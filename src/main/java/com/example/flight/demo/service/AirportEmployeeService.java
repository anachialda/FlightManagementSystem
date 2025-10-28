package com.example.flight.demo.service;

import com.example.flight.demo.model.AirportEmployee;
import com.example.flight.demo.repository.AirportEmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportEmployeeService {
    private final AirportEmployeeRepository employees;

    public AirportEmployeeService(AirportEmployeeRepository employees) {
        this.employees = employees;
    }

    public AirportEmployee save(AirportEmployee e) { return employees.save(e); }
    public boolean delete(Long id) { return employees.delete(id); }
    public Optional<AirportEmployee> find(Long id) { return employees.findById(id); }
    public List<AirportEmployee> all() { return employees.findAll(); }

    public List<AirportEmployee> byDepartment(String department) { return employees.findByDepartment(department);}
}