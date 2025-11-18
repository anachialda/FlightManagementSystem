package com.example.flight.demo.repository;

import com.example.flight.demo.model.AirportEmployee;
import org.springframework.stereotype.Repository;

@Repository
public class AirportEmployeeRepository extends InFileRepository<AirportEmployee> {

    public AirportEmployeeRepository() {
        super("airport_employees.json", AirportEmployee.class);
    }
}
