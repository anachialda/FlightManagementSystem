package com.example.flight.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Ticket {
    private Long id;
    private Long passengerId;
    private Long flightId;
    private double price;
    private String seatNumber;
    private List<Long> luggages = new ArrayList<>();

    public Ticket() {}
    public Ticket(Long id, Long passengerId, Long flightId, double price, String seatNumber) {
        this.id = id; this.passengerId = passengerId; this.flightId = flightId;
        this.price = price; this.seatNumber = seatNumber;
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getPassengerId() { return passengerId; }
    public void setPassengerId(Long passengerId) { this.passengerId = passengerId; }
    public Long getFlightId() { return flightId; }
    public void setFlightId(Long flightId) { this.flightId = flightId; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }
    public List<Long> getLuggages() { return luggages; }
    public void setLuggages(List<Long> luggages) { this.luggages = luggages; }
}
