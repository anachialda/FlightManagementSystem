package com.example.flight.demo.repository;

import com.example.flight.demo.model.AirportEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportEmployeeRepository extends JpaRepository<AirportEmployee, Long> {
}
