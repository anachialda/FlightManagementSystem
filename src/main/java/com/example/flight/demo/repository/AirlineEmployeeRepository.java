package com.example.flight.demo.repository;

import com.example.flight.demo.model.AirlineEmployee;
import java.util.List;
import java.util.Optional;

public interface AirlineEmployeeRepository {
    AirlineEmployee save(AirlineEmployee employee);
    Optional<AirlineEmployee> findById(Long id);
    List<AirlineEmployee> findAll();
    boolean delete(Long id);

}