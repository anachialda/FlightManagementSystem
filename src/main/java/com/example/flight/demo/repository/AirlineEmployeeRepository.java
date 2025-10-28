package com.example.flight.demo.repository;

import com.example.flight.demo.model.AirlineEmployee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AirlineEmployeeRepository implements CrudRepository<AirlineEmployee> {
    private final List<AirlineEmployee> items = new ArrayList<>();
    private long nextId = 1L;

    @Override
    public AirlineEmployee save(AirlineEmployee e) {
        if (e.getId() == null) {
            e.setId(nextId++);
            items.add(e);
        } else {
            delete(e.getId());
            items.add(e);
        }
        return e;
    }

    @Override
    public Optional<AirlineEmployee> findById(Long id) {
        return items.stream().filter(x -> x.getId().equals(id)).findFirst();
    }

    @Override
    public List<AirlineEmployee> findAll() {
        return new ArrayList<>(items);
    }

    @Override
    public boolean delete(Long id) {
        return items.removeIf(x -> x.getId().equals(id));
    }
}
