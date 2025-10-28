package com.example.flight.demo.repository;

import com.example.flight.demo.model.Airplane;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AirplaneRepository implements CrudRepository<Airplane> {
    private final List<Airplane> items = new ArrayList<>();
    private long nextId = 1L;

    @Override
    public Airplane save(Airplane a) {
        if (a.getId() == null) {
            a.setId(nextId++);
            items.add(a);
        } else {
            delete(a.getId());
            items.add(a);
        }
        return a;
    }

    @Override
    public Optional<Airplane> findById(Long id) {
        return items.stream().filter(x -> x.getId().equals(id)).findFirst();
    }

    @Override
    public List<Airplane> findAll() {
        return new ArrayList<>(items);
    }

    @Override
    public boolean delete(Long id) {
        return items.removeIf(x -> x.getId().equals(id));
    }

    // domain helper (optional)
    public Optional<Airplane> findByNumber(int number) {
        return items.stream().filter(x -> x.getNumber() == number).findFirst();
}
}
