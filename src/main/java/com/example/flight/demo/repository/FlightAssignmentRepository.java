package com.example.flight.demo.repository;

import com.example.flight.demo.model.FlightAssignment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FlightAssignmentRepository implements CrudRepository<FlightAssignment> {
    private final List<FlightAssignment> items = new ArrayList<>();
    private long nextId = 1L;

    @Override
    public FlightAssignment save(FlightAssignment fa) {
        if (fa.getId() == null) {
            fa.setId(nextId++);
            items.add(fa);
        } else {
            delete(fa.getId());
            items.add(fa);
        }
        return fa;
    }

    @Override
    public Optional<FlightAssignment> findById(Long id) {
        return items.stream().filter(x -> x.getId().equals(id)).findFirst();
    }

    @Override
    public List<FlightAssignment> findAll() {
        return new ArrayList<>(items);
    }

    @Override
    public boolean delete(Long id) {
        return items.removeIf(x -> x.getId().equals(id));
    }

    // domain helpers
    public List<FlightAssignment> findByFlightId(Long flightId) {
        if (flightId == null) return List.of();
        return items.stream()
                .filter(x -> flightId.equals(x.getFlightId()))
                .toList();
    }

    public List<FlightAssignment> findByStaffId(Long staffId) {
        if (staffId == null) return List.of();
        return items.stream()
                .filter(x -> staffId.equals(x.getStaffId()))
                .toList();}
}