package com.example.flight.demo.service;

import com.example.flight.demo.model.Ticket;
import com.example.flight.demo.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository tickets;

    public TicketService(TicketRepository tickets) {
        this.tickets = tickets;
    }

    // business rule: seat must be unique per flight
    public Ticket issue(Ticket t) {
        if (t.getFlightId() == null || t.getSeatNumber() == null) {
            throw new IllegalArgumentException("flightId and seatNumber are required");
        }
        tickets.findByFlightIdAndSeat(t.getFlightId(), t.getSeatNumber())
                .ifPresent(x -> { throw new IllegalArgumentException("Seat already taken"); });
        return tickets.save(t);
    }

    public boolean cancel(Long ticketId) { return tickets.delete(ticketId); }

    public List<Ticket> all() { return tickets.findAll(); }
}