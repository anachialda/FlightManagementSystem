package com.example.flight.demo.controller;

import com.example.flight.demo.model.NoticeBoard;
import com.example.flight.demo.service.BusinessException;
import com.example.flight.demo.service.NoticeBoardService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/noticeboards")
public class NoticeBoardController {

    private final NoticeBoardService noticeBoardService;

    public NoticeBoardController(NoticeBoardService noticeBoardService) {
        this.noticeBoardService = noticeBoardService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("boards", noticeBoardService.findAll());
        return "noticeboards/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("board", new NoticeBoard());
        return "noticeboards/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        NoticeBoard board = noticeBoardService.findById(id);
        if (board == null) {
            return "redirect:/noticeboards";
        }
        model.addAttribute("board", board);
        return "noticeboards/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("board") NoticeBoard board,
                       BindingResult bindingResult,
                       Model model) {

        if (bindingResult.hasErrors()) {
            return "noticeboards/form";
        }

        try {
            noticeBoardService.save(board);
            return "redirect:/noticeboards";
        } catch (BusinessException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "noticeboards/form";
        }
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        NoticeBoard board = noticeBoardService.findById(id);
        if (board == null) {
            return "redirect:/noticeboards";
        }
        model.addAttribute("board", board);
        return "noticeboards/details";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        try {
            noticeBoardService.delete(id);
            return "redirect:/noticeboards";
        } catch (BusinessException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("boards", noticeBoardService.findAll());
            return "noticeboards/list";
        }
    }
}