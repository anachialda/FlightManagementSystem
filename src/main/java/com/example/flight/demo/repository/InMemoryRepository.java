package com.example.flight.demo.repository;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class InMemoryRepository<T> implements CrudRepository<T> {

    private final List<T> items = new ArrayList<>();
    private long nextId = 1L;

    private final Function<T, Long> getId;
    private final BiConsumer<T, Long> setId;

    public InMemoryRepository(Function<T, Long> getId, BiConsumer<T, Long> setId) {
        this.getId = getId;
        this.setId = setId;
    }

    @Override
    public synchronized T save(T entity) {
        Long id = getId.apply(entity);
        if (id == null) {
            setId.accept(entity, nextId++);
            items.add(entity);
            return entity;
        }

        for (int i = 0; i < items.size(); i++) {
            if (Objects.equals(getId.apply(items.get(i)), id)) {
                items.set(i, entity);
                return entity;
            }
        }
        items.add(entity);
        return entity;
    }

    @Override
    public synchronized Optional<T> findById(Long id) {
        return items.stream().filter(e -> Objects.equals(getId.apply(e), id)).findFirst();
    }

    @Override
    public synchronized List<T> findAll() {
        return new ArrayList<>(items);
    }

    @Override
    public synchronized boolean delete(Long id) {
        return items.removeIf(e -> Objects.equals(getId.apply(e), id));
    }
}
