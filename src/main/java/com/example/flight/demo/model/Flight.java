package com.example.flight.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Flight {

    public enum Status {
        SCHEDULED,
        CANCELLED,
        COMPLETED,
        IN_PROGRESS
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Flugname darf nicht leer sein")
    @Size(min = 3, max = 10, message = "Flugname muss zwischen 3 und 10 Zeichen lang sein (z.B. RO123)")
    @Column(nullable = false, length = 10, unique = true)
    private String name;

    // optional, z.B. manuelle Referenz, kein FK
    @Size(max = 50, message = "NoticeBoard-ID darf maximal 50 Zeichen lang sein")
    private String noticeBoardId;

    @NotNull(message = "Abflugzeit darf nicht leer sein")
    @FutureOrPresent(message = "Abflugzeit darf nicht in der Vergangenheit liegen")
    @Column(nullable = false)
    private LocalDateTime departureTime;

    @NotNull(message = "Ankunftszeit darf nicht leer sein")
    @Column(nullable = false)
    private LocalDateTime arrivalTime;

    @NotNull(message = "Status darf nicht leer sein")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status = Status.SCHEDULED;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airplane_id", nullable = false)
    @NotNull(message = "Flugzeug darf nicht leer sein")
    private Airplane airplane;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "noticeboard_fk")
    private NoticeBoard noticeBoard;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> tickets = new ArrayList<>();

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FlightAssignment> flightAssignments = new ArrayList<>();

    public Flight() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNoticeBoardId() {
        return noticeBoardId;
    }

    public void setNoticeBoardId(String noticeBoardId) {
        this.noticeBoardId = noticeBoardId;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public NoticeBoard getNoticeBoard() {
        return noticeBoard;
    }

    public void setNoticeBoard(NoticeBoard noticeBoard) {
        this.noticeBoard = noticeBoard;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<FlightAssignment> getFlightAssignments() {
        return flightAssignments;
    }

    public void setFlightAssignments(List<FlightAssignment> flightAssignments) {
        this.flightAssignments = flightAssignments;
    }
}