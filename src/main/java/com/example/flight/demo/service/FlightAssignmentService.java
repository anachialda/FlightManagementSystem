package com.example.flight.demo.service;

import com.example.flight.demo.model.FlightAssignment;
import com.example.flight.demo.repository.FlightAssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightAssignmentService {

    private final FlightAssignmentRepository repository;

    public FlightAssignmentService(FlightAssignmentRepository repository) {
        this.repository = repository;
    }

    public List<FlightAssignment> findAll() {
        return repository.findAll();
    }

    public FlightAssignment findById(String id) {
        return repository.findById(id);
    }

    public void save(FlightAssignment assignment) {
        repository.save(assignment);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}