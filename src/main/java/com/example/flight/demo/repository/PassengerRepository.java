package com.example.flight.demo.repository;

import com.example.flight.demo.model.Passenger;
import org.springframework.stereotype.Repository;

@Repository
public class PassengerRepository extends InMemoryRepository<Passenger> {
    public PassengerRepository() {
        super(Passenger::getId, Passenger::setId);
    }
}
