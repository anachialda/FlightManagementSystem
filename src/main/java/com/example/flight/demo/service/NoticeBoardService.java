package com.example.flight.demo.service;

import com.example.flight.demo.model.NoticeBoard;
import com.example.flight.demo.repository.NoticeBoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeBoardService {

    private final NoticeBoardRepository repository;

    public NoticeBoardService(NoticeBoardRepository repository) {
        this.repository = repository;
    }

    public List<NoticeBoard> findAll() {
        return repository.findAll();
    }

    public NoticeBoard findById(String id) {
        return repository.findById(id);
    }

    public void save(NoticeBoard noticeBoard) {
        repository.save(noticeBoard);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }
}