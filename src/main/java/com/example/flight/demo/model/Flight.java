package com.example.flight.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Flight {

    public enum Status {
        SCHEDULED,
        CANCELLED,
        COMPLETED,
        IN_PROGRESS
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;               // e.g. RO123
    private String noticeBoardId;
    private Long airplaneId;           // FK later if needed

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    @ElementCollection
    private List<String> tickets = new ArrayList<>();

    @ElementCollection
    private List<String> flightAssignments = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Status status = Status.SCHEDULED;

    public Flight() {}

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getNoticeBoardId() { return noticeBoardId; }
    public void setNoticeBoardId(String noticeBoardId) { this.noticeBoardId = noticeBoardId; }

    public Long getAirplaneId() { return airplaneId; }
    public void setAirplaneId(Long airplaneId) { this.airplaneId = airplaneId; }

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