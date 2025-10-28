package com.example.flight.demo.model;

public class Luggage {
    private Long id;
    private Long ticketId;
    private String status; // "CheckedIn", "Loaded", "Delivered"

    public Luggage() {}
    public Luggage(Long id, Long ticketId, String status) {
        this.id = id; this.ticketId = ticketId; this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getTicketId() { return ticketId; }
    public void setTicketId(Long ticketId) { this.ticketId = ticketId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

