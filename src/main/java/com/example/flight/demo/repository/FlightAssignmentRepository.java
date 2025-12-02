package com.example.flight.demo.repository;

import com.example.flight.demo.model.FlightAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightAssignmentRepository extends JpaRepository<FlightAssignment, String> {
}
