package com.example.flight.demo.model;

import jakarta.persistence.*;

@Entity
public class Luggage {

    public enum Status {
        CHECKED_IN,
        LOADED,
        DELIVERED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ticketId;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Luggage() {}

    public Luggage(String ticketId, Status status) {
        this.ticketId = ticketId;
        this.status = status;
    }

    public Long getId() { return id; }

    public String getTicketId() { return ticketId; }
    public void setTicketId(String ticketId) { this.ticketId = ticketId; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}
