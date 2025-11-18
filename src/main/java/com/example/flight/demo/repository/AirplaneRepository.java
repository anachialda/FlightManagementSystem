package com.example.flight.demo.repository;

import com.example.flight.demo.model.Airplane;
import org.springframework.stereotype.Repository;

@Repository
public class AirplaneRepository extends InFileRepository<Airplane> {

    public AirplaneRepository() {
        super("airplanes.json", Airplane.class);
    }
}