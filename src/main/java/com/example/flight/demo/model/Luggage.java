package com.example.flight.demo.model;

public class Luggage {

    private enum Status{
        CHECKED_IN,
        LOADED,
        DELIVERED
    }
    private Long id;
    private Long ticketId;
    private Status status;

    public Luggage() {}
    public Luggage(Long id, Long ticketId, Status status) {
        this.id = id; this.ticketId = ticketId; this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getTicketId() { return ticketId; }
    public void setTicketId(Long ticketId) { this.ticketId = ticketId; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}

