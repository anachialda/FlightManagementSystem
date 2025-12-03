package com.example.flight.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "notice_boards")
public class NoticeBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate date;

    @OneToMany(mappedBy = "noticeBoard", cascade = CascadeType.ALL)
    private List<Flight> flightsOfTheDay = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
