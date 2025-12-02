package com.example.flight.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity
public class FlightAssignment {

    @Id
    private String id;

    private String flightId;
    private String staffId; // AirlineEmployee sau AirportEmployee ID

    public FlightAssignment() {
        this.id = UUID.randomUUID().toString(); // generează automat un ID unic
    }

    public FlightAssignment(String flightId, String staffId) {
        this.id = UUID.randomUUID().toString();
        this.flightId = flightId;
        this.staffId = staffId;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }

    public String getStaffId() { return staffId; }
    public void setStaffId(String staffId) { this.staffId = staffId; }
}
