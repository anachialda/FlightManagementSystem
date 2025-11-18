package com.example.flight.demo.repository;

import com.example.flight.demo.model.AirlineEmployee;
import org.springframework.stereotype.Repository;

@Repository
public class AirlineEmployeeRepository extends InFileRepository<AirlineEmployee> {

    public AirlineEmployeeRepository() {
        super("airline_employees.json", AirlineEmployee.class);
    }
}
