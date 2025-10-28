package com.example.flight.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NoticeBoard {
    private Long id;
    private LocalDate date;                  // Java time type ✔
    private List<Long> flightsOfTheDay = new ArrayList<>(); // Flight IDs

    public NoticeBoard() {}
    public NoticeBoard(Long id, LocalDate date) { this.id = id; this.date = date; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public List<Long> getFlightsOfTheDay() { return flightsOfTheDay; }
    public void setFlightsOfTheDay(List<Long> f) { this.flightsOfTheDay = f; }
}
