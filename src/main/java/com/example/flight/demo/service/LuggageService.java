package com.example.flight.demo.service;

import com.example.flight.demo.model.Luggage;
import com.example.flight.demo.repository.LuggageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LuggageService {
    private final LuggageRepository luggages;

    public LuggageService(LuggageRepository luggages) {
        this.luggages = luggages;
    }

    public Luggage save(Luggage l) { return luggages.save(l); }
    public boolean delete(Long id) { return luggages.delete(id); }
    public Optional<Luggage> find(Long id) { return luggages.findById(id); }
    public List<Luggage> all() { return luggages.findAll(); }

}