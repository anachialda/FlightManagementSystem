package com.example.flight.demo.repository;

import com.example.flight.demo.model.NoticeBoard;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class NoticeBoardRepository implements CrudRepository<NoticeBoard> {
    private final List<NoticeBoard> items = new ArrayList<>();
    private long nextId = 1L;

    @Override
    public NoticeBoard save(NoticeBoard b) {
        if (b.getId() == null) {
            b.setId(nextId++);
            items.add(b);
        } else {
            delete(b.getId());
            items.add(b);
        }
        return b;
    }

    @Override
    public Optional<NoticeBoard> findById(Long id) {
        return items.stream().filter(x -> x.getId().equals(id)).findFirst();
    }

    @Override
    public List<NoticeBoard> findAll() {
        return new ArrayList<>(items);
    }

    @Override
    public boolean delete(Long id) {
        return items.removeIf(x -> x.getId().equals(id));
    }

    public Optional<NoticeBoard> findByDate(LocalDate date) {
        if (date == null) return Optional.empty();
        return items.stream()
                .filter(x -> date.equals(x.getDate()))
                .findFirst();}
}