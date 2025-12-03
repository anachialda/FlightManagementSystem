package com.example.flight.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Flugzeugnummer darf nicht leer sein")
    @Positive(message = "Flugzeugnummer muss positiv sein")
    @Column(nullable = false, unique = true)
    private Integer number;

    @OneToMany(mappedBy = "airplane", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Flight> flights = new ArrayList<>();

    public Airplane() {
    }

    public Airplane(Integer number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}