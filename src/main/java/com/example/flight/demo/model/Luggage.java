package com.example.flight.demo.model;

public class Luggage {

    public enum Status {
        CHECKED_IN,
        LOADED,
        DELIVERED
    }

    private String id;
    private String ticketId;
    private Status status;

    public Luggage() {
    }

    public Luggage(String id, String ticketId, Status status) {
        this.id = id;
        this.ticketId = ticketId;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}