package com.example.flight.demo.repository;

import com.example.flight.demo.model.AirlineEmployee;
import org.springframework.stereotype.Repository;

@Repository
public class AirlineEmployeeRepository extends InMemoryRepository<AirlineEmployee> {
    public AirlineEmployeeRepository() {
        super(AirlineEmployee::getId, AirlineEmployee::setId);
    }
}
