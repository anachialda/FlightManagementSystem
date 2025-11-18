package com.example.flight.demo.repository;

import com.example.flight.demo.model.FlightAssignment;
import org.springframework.stereotype.Repository;

@Repository
public class FlightAssignmentRepository extends InFileRepository<FlightAssignment> {

    public FlightAssignmentRepository() {
        super("flight_assignments.json", FlightAssignment.class);
    }
}