package com.example.flight.demo.controller;

import com.example.flight.demo.model.Flight;
import com.example.flight.demo.service.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/flights")
public class FlightController {

    private final FlightService service;

    public FlightController(FlightService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("flights", service.findAll());
        return "flights/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("flight", new Flight());
        return "flights/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Flight flight = service.findById(id);
        if (flight == null) {
            return "redirect:/flights";
        }
        model.addAttribute("flight", flight);
        return "flights/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Flight flight) {
        service.save(flight);
        return "redirect:/flights";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable String id, Model model) {
        Flight flight = service.findById(id);
        if (flight == null) {
            return "redirect:/flights";
        }
        model.addAttribute("flight", flight);
        return "flights/details";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/flights";
    }
}