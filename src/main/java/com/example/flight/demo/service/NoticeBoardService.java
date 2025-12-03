package com.example.flight.demo.service;

import com.example.flight.demo.model.NoticeBoard;
import com.example.flight.demo.repository.NoticeBoardRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NoticeBoardService {

    private final NoticeBoardRepository noticeBoardRepository;

    public NoticeBoardService(NoticeBoardRepository noticeBoardRepository) {
        this.noticeBoardRepository = noticeBoardRepository;
    }

    public List<NoticeBoard> findAll() {
        return noticeBoardRepository.findAll();
    }

    public NoticeBoard findById(Long id) {
        return noticeBoardRepository.findById(id).orElse(null);
    }

    public NoticeBoard save(NoticeBoard noticeBoard) {
        Long id = noticeBoard.getId();
        LocalDate date = noticeBoard.getDate();

        // Unicitate dată
        noticeBoardRepository.findAll().stream()
                .filter(nb -> !nb.getId().equals(id))
                .filter(nb -> nb.getDate().equals(date))
                .findAny()
                .ifPresent(nb -> {
                    throw new BusinessException("Für das Datum " + date + " existiert bereits ein NoticeBoard.");
                });

        return noticeBoardRepository.save(noticeBoard);
    }

    public void delete(Long id) {
        NoticeBoard nb = noticeBoardRepository.findById(id)
                .orElseThrow(() -> new BusinessException("NoticeBoard mit ID " + id + " wurde nicht gefunden."));

        if (!nb.getFlightsOfTheDay().isEmpty()) {
            throw new BusinessException("NoticeBoard kann nicht gelöscht werden, da noch Flüge zugeordnet sind.");
        }

        noticeBoardRepository.delete(nb);
    }
}