package com.example.flight.demo.repository;

import java.util.List;

public interface CrudRepository<T> {

    List<T> findAll();

    T findById(String id);

    void save(T entity);

    void deleteById(String id);
}