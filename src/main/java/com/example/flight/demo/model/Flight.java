package com.example.flight.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Flight {

    private enum Status {
        SCHEDULED,
        CANCELLED,
        COMPLETED,
        IN_PROGRESS
    }

    private Long id;
    private String name;        // e.g., "RO123"
    private Long noticeBoardId; // link
    private Long airplaneId;    // link
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private List<Long> tickets = new ArrayList<>();
    private List<Long> flightAssignments = new ArrayList<>();
    private Status status = Status.SCHEDULED;

    public Flight() {}

    public Flight(Long id, String name, Long noticeBoardId, Long airplaneId,
                  LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.id = id; this.name = name; this.noticeBoardId = noticeBoardId;
        this.airplaneId = airplaneId; this.departureTime = departureTime; this.arrivalTime = arrivalTime;
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Long getNoticeBoardId() { return noticeBoardId; }
    public void setNoticeBoardId(Long noticeBoardId) { this.noticeBoardId = noticeBoardId; }
    public Long getAirplaneId() { return airplaneId; }
    public void setAirplaneId(Long airplaneId) { this.airplaneId = airplaneId; }
    public LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }
    public LocalDateTime getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(LocalDateTime arrivalTime) { this.arrivalTime = arrivalTime; }
    public List<Long> getTickets() { return tickets; }
    public void setTickets(List<Long> tickets) { this.tickets = tickets; }
    public List<Long> getFlightAssignments() { return flightAssignments; }
    public void setFlightAssignments(List<Long> flightAssignments) { this.flightAssignments = flightAssignments; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

}
