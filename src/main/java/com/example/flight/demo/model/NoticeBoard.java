package com.example.flight.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NoticeBoard {

    private String id;
    private LocalDate date;
    private List<String> flightsOfTheDay = new ArrayList<>(); // Flight IDs

    public NoticeBoard() {
    }

    public NoticeBoard(String id, LocalDate date) {
        this.id = id;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<String> getFlightsOfTheDay() {
        return flightsOfTheDay;
    }

    public void setFlightsOfTheDay(List<String> flightsOfTheDay) {
        this.flightsOfTheDay = flightsOfTheDay;
    }
}