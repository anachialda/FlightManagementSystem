package com.example.flight.demo.service;

import com.example.flight.demo.model.Airplane;
import com.example.flight.demo.repository.AirplaneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirplaneService {
    private final AirplaneRepository airplanes;

    public AirplaneService(AirplaneRepository airplanes) {
        this.airplanes = airplanes;
    }

    public Airplane save(Airplane a) { return airplanes.save(a); }
    public boolean delete(Long id) { return airplanes.delete(id); }
    public Optional<Airplane> find(Long id) { return airplanes.findById(id); }
    public List<Airplane> all() { return airplanes.findAll(); }
}