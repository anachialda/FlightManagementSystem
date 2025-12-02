package com.example.flight.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class NoticeBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ElementCollection
    private List<String> flightsOfTheDay = new ArrayList<>();

    public NoticeBoard() {}

    public NoticeBoard(LocalDate date) {
        this.date = date;
    }

    public Long getId() { return id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public List<String> getFlightsOfTheDay() { return flightsOfTheDay; }
    public void setFlightsOfTheDay(List<String> flightsOfTheDay) { this.flightsOfTheDay = flightsOfTheDay; }
}
