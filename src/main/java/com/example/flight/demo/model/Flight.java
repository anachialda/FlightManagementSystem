package com.example.flight.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Flight {

    public enum Status {
        SCHEDULED,
        CANCELLED,
        COMPLETED,
        IN_PROGRESS
    }

    private String id;
    private String name;        // e.g., "RO123"
    private String noticeBoardId; // link
    private String airplaneId;    // link
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private List<String> tickets = new ArrayList<>();
    private List<String> flightAssignments = new ArrayList<>();
    private Status status = Status.SCHEDULED;

    public Flight() {}

    public Flight(String id, String name, String noticeBoardId, String airplaneId,
                  LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.id = id;
        this.name = name;
        this.noticeBoardId = noticeBoardId;
        this.airplaneId = airplaneId;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNoticeBoardId() { return noticeBoardId; }
    public void setNoticeBoardId(String noticeBoardId) { this.noticeBoardId = noticeBoardId; }

    public String getAirplaneId() { return airplaneId; }
    public void setAirplaneId(String airplaneId) { this.airplaneId = airplaneId; }

    public LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }

    public LocalDateTime getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(LocalDateTime arrivalTime) { this.arrivalTime = arrivalTime; }

    public List<String> getTickets() { return tickets; }
    public void setTickets(List<String> tickets) { this.tickets = tickets; }

    public List<String> getFlightAssignments() { return flightAssignments; }
    public void setFlightAssignments(List<String> flightAssignments) { this.flightAssignments = flightAssignments; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}