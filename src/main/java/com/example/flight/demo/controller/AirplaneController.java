package com.example.flight.demo.controller;

import com.example.flight.demo.model.Airplane;
import com.example.flight.demo.service.AirplaneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/airplanes")
public class AirplaneController {

    private final AirplaneService service;

    public AirplaneController(AirplaneService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("airplanes", service.findAll());
        return "airplanes/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("airplane", new Airplane());
        return "airplanes/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Airplane airplane = service.findById(id);
        if (airplane == null) {
            return "redirect:/airplanes";
        }
        model.addAttribute("airplane", airplane);
        return "airplanes/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Airplane airplane) {
        service.save(airplane);
        return "redirect:/airplanes";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable String id, Model model) {
        Airplane airplane = service.findById(id);
        if (airplane == null) {
            return "redirect:/airplanes";
        }
        model.addAttribute("airplane", airplane);
        return "airplanes/details";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/airplanes";
    }
}