package com.example.flight.demo.repository;

import com.example.flight.demo.model.Passenger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PassengerRepository implements CrudRepository<Passenger> {
    private final List<Passenger> items = new ArrayList<>();
    private long nextId = 1L;

    @Override
    public Passenger save(Passenger p) {
        if (p.getId() == null) {
            p.setId(nextId++);
            items.add(p);
        } else {
            delete(p.getId());
            items.add(p);
        }
        return p;
    }

    @Override
    public Optional<Passenger> findById(Long id) {
        return items.stream().filter(x -> x.getId().equals(id)).findFirst();
    }

    @Override
    public List<Passenger> findAll() {
        return new ArrayList<>(items);
    }

    @Override
    public boolean delete(Long id) {
        return items.removeIf(x -> x.getId().equals(id));
    }

}