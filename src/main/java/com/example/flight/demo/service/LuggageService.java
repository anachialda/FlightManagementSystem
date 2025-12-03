package com.example.flight.demo.service;

import com.example.flight.demo.model.Luggage;
import com.example.flight.demo.model.Ticket;
import com.example.flight.demo.repository.LuggageRepository;
import com.example.flight.demo.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LuggageService {

    private final LuggageRepository luggageRepository;
    private final TicketRepository ticketRepository;

    public LuggageService(LuggageRepository luggageRepository,
                          TicketRepository ticketRepository) {
        this.luggageRepository = luggageRepository;
        this.ticketRepository = ticketRepository;
    }

    public List<Luggage> findAll() {
        return luggageRepository.findAll();
    }

    public Luggage findById(Long id) {
        return luggageRepository.findById(id).orElse(null);
    }

    public Luggage save(Luggage luggage) {
        Ticket ticket = luggage.getTicket();
        if (ticket == null || ticket.getId() == null ||
                ticketRepository.findById(ticket.getId()).isEmpty()) {
            throw new BusinessException("Ausgewähltes Ticket existiert nicht.");
        }
        return luggageRepository.save(luggage);
    }

    public void delete(Long id) {
        luggageRepository.deleteById(id);
    }
}