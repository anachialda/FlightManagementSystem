package com.example.flight.demo.repository;

import com.example.flight.demo.model.Flight;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FlightRepository implements CrudRepository<Flight> {
    private final List<Flight> items = new ArrayList<>();
    private long nextId = 1L;

    @Override
    public Flight save(Flight f) {
        if (f.getId() == null) {
            f.setId(nextId++);
            items.add(f);
        } else {
            delete(f.getId());
            items.add(f);
        }
        return f;
    }

    @Override
    public Optional<Flight> findById(Long id) {
        return items.stream().filter(x -> x.getId().equals(id)).findFirst();
    }

    @Override
    public List<Flight> findAll() {
        return new ArrayList<>(items);
    }

    @Override
    public boolean delete(Long id) {
        return items.removeIf(x -> x.getId().equals(id));
    }

}