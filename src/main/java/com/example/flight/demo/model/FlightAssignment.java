package com.example.flight.demo.model;

public class FlightAssignment {
    private Long id;
    private Long flightId;
    private Long staffId; // AirlineEmployee or AirportEmployee ID

    public FlightAssignment() {}
    public FlightAssignment(Long id, Long flightId, Long staffId) {
        this.id = id; this.flightId = flightId; this.staffId = staffId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }
    public Long getStaffId() { return staffId; }
    public void setStaffId(Long staffId) { this.staffId = staffId; }
}
