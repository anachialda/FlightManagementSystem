package com.example.flight.demo.repository;

import com.example.flight.demo.model.AirlineEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineEmployeeRepository extends JpaRepository<AirlineEmployee, Long> {
}
