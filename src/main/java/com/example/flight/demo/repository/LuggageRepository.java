package com.example.flight.demo.repository;

import com.example.flight.demo.model.Luggage;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class LuggageRepository implements CrudRepository<Luggage> {
    private final List<Luggage> items = new ArrayList<>();
    private long nextId = 1L;

    @Override
    public Luggage save(Luggage l) {
        if (l.getId() == null) {
            l.setId(nextId++);
            items.add(l);
        } else {
            delete(l.getId());
            items.add(l);
        }
        return l;
    }

    @Override
    public Optional<Luggage> findById(Long id) {
        return items.stream().filter(x -> x.getId().equals(id)).findFirst();
    }

    @Override
    public List<Luggage> findAll() {
        return new ArrayList<>(items);
    }

    @Override
    public boolean delete(Long id) {
        return items.removeIf(x -> x.getId().equals(id));
    }

}