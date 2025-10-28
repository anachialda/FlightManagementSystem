package com.example.flight.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Airplane {
    private Long id;
    private int number;                   // plane number
    private List<Long> flights = new ArrayList<>(); // Flight IDs

    public Airplane() {}
    public Airplane(Long id, int number) { this.id = id; this.number = number; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getNumber() { return number; }
    public void setNumber(int number) { this.number = number; }
    public List<Long> getFlights() { return flights; }
    public void setFlights(List<Long> flights) { this.flights = flights; }
}
