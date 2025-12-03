package com.example.flight.demo.controller;

import com.example.flight.demo.model.NoticeBoard;
import com.example.flight.demo.repository.NoticeBoardRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notice-boards")
public class NoticeBoardController {

    private final NoticeBoardRepository repository;

    public NoticeBoardController(NoticeBoardRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("noticeBoards", repository.findAll());
        return "noticeBoards/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("noticeBoard", new NoticeBoard());
        return "noticeBoards/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("noticeBoard", repository.findById(id).orElseThrow());
        return "noticeBoards/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("noticeBoard") NoticeBoard noticeBoard, BindingResult result) {
        if (result.hasErrors()) {
            return "noticeBoards/form";
        }
        repository.save(noticeBoard);
        return "redirect:/notice-boards";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/notice-boards";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("noticeBoard", repository.findById(id).orElseThrow());
        return "noticeBoards/details";
    }
}
