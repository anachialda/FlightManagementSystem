package com.example.flight.demo.service;

import com.example.flight.demo.model.Ticket;
import com.example.flight.demo.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository repository;

    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }

    public List<Ticket> findAll() {
        return repository.findAll();
    }

    public Ticket findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void save(Ticket ticket) {
        repository.save(ticket);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
