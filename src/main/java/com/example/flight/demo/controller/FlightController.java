package com.example.flight.demo.controller;

import com.example.flight.demo.model.Flight;
import com.example.flight.demo.repository.FlightRepository;
import com.example.flight.demo.service.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/flights")
public class FlightController {
    private final FlightService service;
    private final FlightRepository repo; // for delete (simple)

    public FlightController(FlightService service, FlightRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    private static final DateTimeFormatter FORM_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    @GetMapping
    public String index(Model model) {
        model.addAttribute("flights", service.all());
        return "flight/index";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("flight", new Flight());
        return "flight/form";
    }

    // We accept strings for times and parse them with a simple formatter
    @PostMapping
    public String create(
            @RequestParam String name,
            @RequestParam(required = false) Long noticeBoardId,
            @RequestParam(required = false) Long airplaneId,
            @RequestParam String departureTime,
            @RequestParam String arrivalTime
    ) {
        LocalDateTime dep = LocalDateTime.parse(departureTime, FORM_FMT);
        LocalDateTime arr = LocalDateTime.parse(arrivalTime, FORM_FMT);
        Flight f = new Flight(null, name, noticeBoardId, airplaneId, dep, arr);
        service.save(f);
        return "redirect:/flights";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        repo.delete(id);
        return "redirect:/flights";
    }
}
