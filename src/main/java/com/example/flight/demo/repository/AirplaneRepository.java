package com.example.flight.demo.repository;

import com.example.flight.demo.model.Airplane;
import org.springframework.stereotype.Repository;

@Repository
public class AirplaneRepository extends InMemoryRepository<Airplane> {
    public AirplaneRepository() {
        super(Airplane::getId, Airplane::setId);
    }
}
