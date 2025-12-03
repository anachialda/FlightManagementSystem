package com.example.flight.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class NoticeBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Datum darf nicht leer sein")
    @Column(nullable = false)
    private LocalDate date;

    @OneToMany(mappedBy = "noticeBoard", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Flight> flightsOfTheDay = new ArrayList<>();

    public NoticeBoard() {
    }

    public NoticeBoard(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Flight> getFlightsOfTheDay() {
        return flightsOfTheDay;
    }

    public void setFlightsOfTheDay(List<Flight> flightsOfTheDay) {
        this.flightsOfTheDay = flightsOfTheDay;
    }
}