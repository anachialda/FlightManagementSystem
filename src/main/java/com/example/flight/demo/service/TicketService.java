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
    public boolean cancel(Long ticketId) { return tickets.deleteById(ticketId); }

    public Ticket save(Ticket t) { return tickets.save(t); }
    public List<Ticket> all() { return tickets.findAll(); }
}