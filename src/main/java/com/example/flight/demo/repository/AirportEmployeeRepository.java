package com.example.flight.demo.repository;

import com.example.flight.demo.model.AirportEmployee;
import org.springframework.stereotype.Repository;

@Repository
public class AirportEmployeeRepository extends InMemoryRepository<AirportEmployee> {
    public AirportEmployeeRepository() {
        super(AirportEmployee::getId, AirportEmployee::setId);
    }
}
