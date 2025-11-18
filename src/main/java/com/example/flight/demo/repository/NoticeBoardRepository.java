package com.example.flight.demo.repository;

import com.example.flight.demo.model.NoticeBoard;
import org.springframework.stereotype.Repository;

@Repository
public class NoticeBoardRepository extends InFileRepository<NoticeBoard> {

    public NoticeBoardRepository() {
        super("notice_boards.json", NoticeBoard.class);
    }
}