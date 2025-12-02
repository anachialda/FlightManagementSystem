package com.example.flight.demo.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passengerId;
    private String flightId;
    private double price;
    private String seatNumber;

    @ElementCollection
    private List<String> luggages = new ArrayList<>();

    public Ticket() {}

    public Ticket(String passengerId, String flightId, double price, String seatNumber) {
        this.passengerId = passengerId;
        this.flightId = flightId;
        this.price = price;
        this.seatNumber = seatNumber;
    }

    public Long getId() { return id; }

    public String getPassengerId() { return passengerId; }
    public void setPassengerId(String passengerId) { this.passengerId = passengerId; }

    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }

    public List<String> getLuggages() { return luggages; }
    public void setLuggages(List<String> luggages) { this.luggages = luggages; }
}
