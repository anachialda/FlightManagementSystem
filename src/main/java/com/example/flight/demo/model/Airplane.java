package com.example.flight.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Airplane {

    private String id;
    private int number;                     // plane number
    private List<String> flights = new ArrayList<>(); // Flight IDs

    public Airplane() {
    }

    public Airplane(String id, int number) {
        this.id = id;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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