package com.example.flight.demo.service;

import com.example.flight.demo.model.NoticeBoard;
import com.example.flight.demo.repository.NoticeBoardRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NoticeBoardService {
    private final NoticeBoardRepository boards;

    public NoticeBoardService(NoticeBoardRepository boards) {
        this.boards = boards;
    }

    public NoticeBoard save(NoticeBoard b) { return boards.save(b); }
    public boolean delete(Long id) { return boards.delete(id); }
    public Optional<NoticeBoard> find(Long id) { return boards.findById(id); }
    public List<NoticeBoard> all() { return boards.findAll(); }

}