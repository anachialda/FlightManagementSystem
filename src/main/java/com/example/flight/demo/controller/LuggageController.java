package com.example.flight.demo.controller;

import com.example.flight.demo.model.Luggage;
import com.example.flight.demo.service.LuggageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/luggage")
public class LuggageController {

    private final LuggageService service;

    public LuggageController(LuggageService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("luggageList", service.findAll());
        return "luggage/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("luggage", new Luggage());
        return "luggage/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Luggage luggage = service.findById(id);
        if (luggage == null) return "redirect:/luggage";
        model.addAttribute("luggage", luggage);
        return "luggage/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Luggage luggage) {
        service.save(luggage);
        return "redirect:/luggage";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        Luggage luggage = service.findById(id);
        if (luggage == null) return "redirect:/luggage";
        model.addAttribute("luggage", luggage);
        return "luggage/details";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/luggage";
    }
}
