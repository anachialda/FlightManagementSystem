package com.example.flight.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity
public class FlightAssignment {

    @Id
    private String id;

    private String flightId;
    private String staffId;

    public FlightAssignment() {
        this.id = UUID.randomUUID().toString();
    }

    public FlightAssignment(String flightId, String staffId) {
        this.id = UUID.randomUUID().toString();
        this.flightId = flightId;
        this.staffId = staffId;
    }

    public String getId() { return id; }
    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public String getStaffId() { return staffId; }
    public void setStaffId(String staffId) { this.staffId = staffId; }
}
