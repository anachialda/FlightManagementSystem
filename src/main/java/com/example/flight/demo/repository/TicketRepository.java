package com.example.flight.demo.repository;

import com.example.flight.demo.model.Ticket;
import org.springframework.stereotype.Repository;

@Repository
public class TicketRepository extends InFileRepository<Ticket> {

    public TicketRepository() {
        super("tickets.json", Ticket.class);
    }
}