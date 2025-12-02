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
    public String showEditForm(@PathVariable Long id, Model model) {
        Airplane airplane = service.findById(id);
        if (airplane == null) return "redirect:/airplanes";

        model.addAttribute("airplane", airplane);
        return "airplanes/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Airplane airplane,
                       @RequestParam(required = false) String flightsInput) {

        if (flightsInput != null && !flightsInput.isBlank()) {
            airplane.setFlights(
                    java.util.Arrays.stream(flightsInput.split(","))
                            .map(String::trim)
                            .toList()
            );
        }

        service.save(airplane);
        return "redirect:/airplanes";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        Airplane airplane = service.findById(id);
        if (airplane == null) return "redirect:/airplanes";

        model.addAttribute("airplane", airplane);
        return "airplanes/details";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/airplanes";
    }
}