package com.example.flight.demo.service;

import com.example.flight.demo.model.Luggage;
import com.example.flight.demo.repository.LuggageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LuggageService {

    private final LuggageRepository repository;

    public LuggageService(LuggageRepository repository) {
        this.repository = repository;
    }

    public List<Luggage> findAll() {
        return repository.findAll();
    }

    public Luggage findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void save(Luggage luggage) {
        repository.save(luggage);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
