package com.example.flight.demo.repository;

import com.example.flight.demo.model.Ticket;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TicketRepository implements CrudRepository<Ticket> {
    private final List<Ticket> items = new ArrayList<>();
    private long nextId = 1L;

    @Override
    public Ticket save(Ticket t) {
        if (t.getId() == null) {
            t.setId(nextId++);
            items.add(t);
        } else {
            delete(t.getId());
            items.add(t);
        }
        return t;
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return items.stream().filter(x -> x.getId().equals(id)).findFirst();
    }

    @Override
    public List<Ticket> findAll() {
        return new ArrayList<>(items);
    }

    @Override
    public boolean delete(Long id) {
        return items.removeIf(x -> x.getId().equals(id));
    }

    // domain helpers
    public List<Ticket> findByFlightId(Long flightId) {
        return items.stream()
                .filter(x -> x.getFlightId() != null && x.getFlightId().equals(flightId))
                .toList();
    }

    public Optional<Ticket> findByFlightIdAndSeat(Long flightId, String seatNumber) {
        if (seatNumber == null) return Optional.empty();
        return items.stream()
                .filter(x -> x.getFlightId() != null && x.getFlightId().equals(flightId))
                .filter(x -> x.getSeatNumber() != null && x.getSeatNumber().equalsIgnoreCase(seatNumber))
                .findFirst();
    }

    public List<Ticket> findByPassengerId(Long passengerId) {
        return items.stream()
                .filter(x -> x.getPassengerId() != null && x.getPassengerId().equals(passengerId))
                .toList();
    }
}