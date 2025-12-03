package com.example.flight.demo.service;

import com.example.flight.demo.model.Flight;
import com.example.flight.demo.model.Passenger;
import com.example.flight.demo.model.Ticket;
import com.example.flight.demo.repository.FlightRepository;
import com.example.flight.demo.repository.PassengerRepository;
import com.example.flight.demo.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final PassengerRepository passengerRepository;
    private final FlightRepository flightRepository;

    public TicketService(TicketRepository ticketRepository,
                         PassengerRepository passengerRepository,
                         FlightRepository flightRepository) {
        this.ticketRepository = ticketRepository;
        this.passengerRepository = passengerRepository;
        this.flightRepository = flightRepository;
    }

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Ticket findById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public Ticket save(Ticket ticket) {
        validateTicketBusinessRules(ticket);
        return ticketRepository.save(ticket);
    }

    private void validateTicketBusinessRules(Ticket ticket) {
        Passenger p = ticket.getPassenger();
        if (p == null || p.getId() == null ||
                passengerRepository.findById(p.getId()).isEmpty()) {
            throw new BusinessException("Ausgewählter Passagier existiert nicht.");
        }

        Flight f = ticket.getFlight();
        if (f == null || f.getId() == null ||
                flightRepository.findById(f.getId()).isEmpty()) {
            throw new BusinessException("Ausgewählter Flug existiert nicht.");
        }

        // Wird der Sitz bereits in diesem Flug benutzt?
        Long ticketId = ticket.getId();
        String seat = ticket.getSeatNumber();
        Long flightId = f.getId();

        ticketRepository.findAll().stream()
                .filter(t -> t.getFlight() != null && t.getFlight().getId().equals(flightId))
                .filter(t -> !t.getId().equals(ticketId))
                .filter(t -> t.getSeatNumber().equalsIgnoreCase(seat))
                .findAny()
                .ifPresent(t -> {
                    throw new BusinessException("Sitz " + seat + " ist für diesen Flug bereits belegt.");
                });
    }

    public void delete(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Ticket mit ID " + id + " wurde nicht gefunden."));

        if (!ticket.getLuggages().isEmpty()) {
            throw new BusinessException("Ticket kann nicht gelöscht werden, da noch Gepäckstücke vorhanden sind.");
        }

        ticketRepository.delete(ticket);
    }
}