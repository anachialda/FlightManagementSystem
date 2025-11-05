package com.example.flight.demo.controller;

import com.example.flight.demo.model.NoticeBoard;
import com.example.flight.demo.service.NoticeBoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/boards")
public class NoticeBoardController {
    private final NoticeBoardService service;

    public NoticeBoardController(NoticeBoardService service) {
        this.service = service;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("boards", service.all());
        return "board/index";
    }

    @GetMapping("/new")
    public String form(Model model){
        model.addAttribute("board", new NoticeBoard());
        return "board/form";
    }

    @PostMapping
    public String create(@RequestParam String date){

        NoticeBoard nb = new NoticeBoard();
        nb.setId(null);
        nb.setDate(LocalDate.parse(date));
        service.save(nb);
        return "redirect:/boards";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        service.delete(id);
        return "redirect:/boards";
    }
}
