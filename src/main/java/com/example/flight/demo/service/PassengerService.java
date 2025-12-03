package com.example.flight.demo.service;

import com.example.flight.demo.model.Passenger;
import com.example.flight.demo.repository.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {

    private final PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public List<Passenger> findAll() {
        return passengerRepository.findAll();
    }

    public Passenger findById(Long id) {
        return passengerRepository.findById(id).orElse(null);
    }

    public Passenger save(Passenger passenger) {
        Long id = passenger.getId();

        // Unicitate e-mail
        passengerRepository.findAll().stream()
                .filter(p -> !p.getId().equals(id))
                .filter(p -> p.getEmail().equalsIgnoreCase(passenger.getEmail()))
                .findAny()
                .ifPresent(p -> {
                    throw new BusinessException("E-Mail '" + passenger.getEmail() + "' ist bereits vergeben.");
                });

        return passengerRepository.save(passenger);
    }

    public void delete(Long id) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Passagier mit ID " + id + " wurde nicht gefunden."));

        if (!passenger.getTickets().isEmpty()) {
            throw new BusinessException("Passagier kann nicht gelöscht werden, da noch Tickets vorhanden sind.");
        }

        passengerRepository.delete(passenger);
    }
}