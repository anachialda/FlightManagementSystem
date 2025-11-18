package com.example.flight.demo.repository;

import com.example.flight.demo.model.Luggage;
import org.springframework.stereotype.Repository;

@Repository
public class LuggageRepository extends InFileRepository<Luggage> {

    public LuggageRepository() {
        super("luggage.json", Luggage.class);
    }
}