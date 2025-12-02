package com.example.flight.demo.controller;

import com.example.flight.demo.model.NoticeBoard;
import com.example.flight.demo.service.NoticeBoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notice-boards")
public class NoticeBoardController {

    private final NoticeBoardService service;

    public NoticeBoardController(NoticeBoardService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("noticeBoards", service.findAll());
        return "noticeBoards/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("noticeBoard", new NoticeBoard());
        return "noticeBoards/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        NoticeBoard noticeBoard = service.findById(id);
        if (noticeBoard == null) return "redirect:/notice-boards";
        model.addAttribute("noticeBoard", noticeBoard);
        return "noticeBoards/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute NoticeBoard noticeBoard,
                       @RequestParam(required = false) String flightsInput) {

        if (flightsInput != null && !flightsInput.isBlank()) {
            noticeBoard.setFlightsOfTheDay(
                    java.util.Arrays.stream(flightsInput.split(","))
                            .map(String::trim)
                            .toList()
            );
        }

        service.save(noticeBoard);
        return "redirect:/notice-boards";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        NoticeBoard noticeBoard = service.findById(id);
        if (noticeBoard == null) return "redirect:/notice-boards";
        model.addAttribute("noticeBoard", noticeBoard);
        return "noticeBoards/details";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/notice-boards";
    }
}
