package com.example.flight.demo.validation;

import com.example.flight.demo.model.FlightAssignment;

public class FlightAssignmentValidation {

    public static void validateFlightAssignment(FlightAssignment assignment) {
        if (assignment == null) {
            throw new RuntimeException("Date assignment invalide.");
        }

        // Validare Flight ID
        if (assignment.getFlightId() == null || assignment.getFlightId().trim().isEmpty()) {
            throw new RuntimeException("Flight ID invalid.");
        }

        // Validare Staff ID
        if (assignment.getStaffId() == null || assignment.getStaffId().trim().isEmpty()) {
            throw new RuntimeException("Staff ID invalid.");
        }
    }
}
