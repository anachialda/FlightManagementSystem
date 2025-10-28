package com.example.flight.demo.service;

import com.example.flight.demo.model.Flight;
import com.example.flight.demo.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FlightService {
    private final FlightRepository flights;

    public FlightService(FlightRepository flights) {
        this.flights = flights;
    }

    public Flight schedule(Flight f) {
        if (f.getDepartureTime() == null || f.getArrivalTime() == null) {
            throw new IllegalArgumentException("departureTime and arrivalTime are required");
        }
        if (f.getArrivalTime().isBefore(f.getDepartureTime())) {
            throw new IllegalArgumentException("arrivalTime must be after departureTime");
        }
        return flights.save(f);
    }

    public List<Flight> all() { return flights.findAll(); }
    public List<Flight> byDate(LocalDate date) { return flights.findByDate(date); }

    public void delay(Long id) { setStatus(id, "DELAYED"); }
    public void cancel(Long id) { setStatus(id, "CANCELLED"); }

    private void setStatus(Long id, String status) {
        flights.findById(id).ifPresent(f -> {
            f.setStatus(status);
            flights.save(f);
        }
        );}
}