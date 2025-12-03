package com.example.flight.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "luggages")
public class Luggage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private Ticket ticket;

    @Enumerated(EnumType.STRING)
    @NotNull
    private LuggageStatus status = LuggageStatus.CHECKED_IN;

    @Transient
    private Long ticketId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public LuggageStatus getStatus() {
        return status;
    }

    public void setStatus(LuggageStatus status) {
        this.status = status;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }
}
