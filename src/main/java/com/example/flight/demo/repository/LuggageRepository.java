package com.example.flight.demo.repository;

import com.example.flight.demo.model.Luggage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LuggageRepository extends JpaRepository<Luggage, Long> {
}
