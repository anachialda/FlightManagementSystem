package com.example.flight.demo.service;

import com.example.flight.demo.model.Airplane;
import com.example.flight.demo.repository.AirplaneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirplaneService {

    private final AirplaneRepository airplaneRepository;

    public AirplaneService(AirplaneRepository airplaneRepository) {
        this.airplaneRepository = airplaneRepository;
    }

    public List<Airplane> findAll() {
        return airplaneRepository.findAll();
    }

    public Airplane findById(Long id) {
        return airplaneRepository.findById(id).orElse(null);
    }

    public Airplane save(Airplane airplane) {
        Long id = airplane.getId();

        // Unicitate număr avion
        airplaneRepository.findAll().stream()
                .filter(a -> !a.getId().equals(id))
                .filter(a -> a.getNumber().equals(airplane.getNumber()))
                .findAny()
                .ifPresent(a -> {
                    throw new BusinessException("Flugzeugnummer '" + airplane.getNumber() + "' ist bereits vergeben.");
                });

        return airplaneRepository.save(airplane);
    }

    public void delete(Long id) {
        Airplane airplane = airplaneRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Flugzeug mit ID " + id + " wurde nicht gefunden."));

        if (!airplane.getFlights().isEmpty()) {
            throw new BusinessException("Flugzeug kann nicht gelöscht werden, da noch Flüge damit verbunden sind.");
        }

        airplaneRepository.delete(airplane);
    }
}