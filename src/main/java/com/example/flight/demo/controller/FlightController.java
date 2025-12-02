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
        model.addAttribute("statuses", Flight.Status.values());
        return "flights/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Flight flight = service.findById(id);
        if (flight == null) return "redirect:/flights";

        model.addAttribute("flight", flight);
        model.addAttribute("statuses", Flight.Status.values());
        return "flights/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Flight flight,
                       @RequestParam(required = false) String ticketsInput,
                       @RequestParam(required = false) String assignmentsInput) {

        if (ticketsInput != null)
            flight.setTickets(
                    java.util.Arrays.stream(ticketsInput.split(","))
                            .map(String::trim)
                            .filter(s -> !s.isEmpty())
                            .toList()
            );

        if (assignmentsInput != null)
            flight.setFlightAssignments(
                    java.util.Arrays.stream(assignmentsInput.split(","))
                            .map(String::trim)
                            .filter(s -> !s.isEmpty())
                            .toList()
            );

        service.save(flight);
        return "redirect:/flights";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        Flight flight = service.findById(id);
        if (flight == null) return "redirect:/flights";

        model.addAttribute("flight", flight);
        return "flights/details";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/flights";
    }
}