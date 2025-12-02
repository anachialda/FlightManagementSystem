package com.example.flight.demo.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int number;

    // Just store flight IDs as strings for now (simple)
    @ElementCollection
    private List<String> flights = new ArrayList<>();

    public Airplane() {}

    public Airplane(int number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<String> getFlights() {
        return flights;
    }

    public void setFlights(List<String> flights) {
        this.flights = flights;
    }
}