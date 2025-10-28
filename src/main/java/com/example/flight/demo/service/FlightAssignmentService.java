package com.example.flight.demo.service;

import com.example.flight.demo.model.FlightAssignment;
import com.example.flight.demo.repository.FlightAssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightAssignmentService {
    private final FlightAssignmentRepository assignments;

    public FlightAssignmentService(FlightAssignmentRepository assignments) {
        this.assignments = assignments;
    }

    public FlightAssignment save(FlightAssignment fa) { return assignments.save(fa); }
    public boolean delete(Long id) { return assignments.delete(id); }
    public Optional<FlightAssignment> find(Long id) { return assignments.findById(id); }
    public List<FlightAssignment> all() { return assignments.findAll(); }
}