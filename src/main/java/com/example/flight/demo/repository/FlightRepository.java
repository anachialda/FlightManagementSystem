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

    // domain helpers (optional)
    public List<Flight> findByDate(LocalDate date) {
        if (date == null) return List.of();
        return items.stream()
                .filter(x -> x.getDepartureTime() != null && x.getDepartureTime().toLocalDate().equals(date))
                .toList();
    }

    public List<Flight> findByAirplaneId(Long airplaneId) {
        if (airplaneId == null) return List.of();
        return items.stream()
                .filter(x -> airplaneId.equals(x.getAirplaneId()))
                .toList();
    }

    public List<Flight> findByNoticeBoardId(Long noticeBoardId) {
        if (noticeBoardId == null) return List.of();
        return items.stream()
                .filter(x -> noticeBoardId.equals(x.getNoticeBoardId()))
                .toList();
    }

    public List<Flight> findByStatus(String status) {
        if (status == null) return List.of();
        return items.stream()
                .filter(x -> x.getStatus() != null && x.getStatus().equalsIgnoreCase(status))
                .toList();
}
}