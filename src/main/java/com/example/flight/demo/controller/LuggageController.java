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
    public String index(Model model){
        model.addAttribute("luggages", service.all());
        return "luggage/index";
    }

    @GetMapping("/new")
    public String form(Model model){
        model.addAttribute("luggage", new Luggage());
        return "luggage/form";
    }

    @PostMapping
    public String create(@ModelAttribute Luggage luggage){
        luggage.setId(null); // auto-ID
        service.save(luggage);
        return "redirect:/luggage";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        service.delete(id);
        return "redirect:/luggage";
    }
}