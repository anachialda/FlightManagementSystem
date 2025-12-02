package com.example.flight.demo.controller;

import com.example.flight.demo.model.Ticket;
import com.example.flight.demo.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService service;

    public TicketController(TicketService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("tickets", service.findAll());
        return "tickets/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "tickets/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Ticket ticket = service.findById(id);
        if (ticket == null) return "redirect:/tickets";
        model.addAttribute("ticket", ticket);
        return "tickets/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Ticket ticket,
                       @RequestParam(required = false) String luggagesInput) {

        if (luggagesInput != null && !luggagesInput.isBlank()) {
            ticket.setLuggages(
                    java.util.Arrays.stream(luggagesInput.split(","))
                            .map(String::trim)
                            .toList()
            );
        }

        service.save(ticket);
        return "redirect:/tickets";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        Ticket ticket = service.findById(id);
        if (ticket == null) return "redirect:/tickets";
        model.addAttribute("ticket", ticket);
        return "tickets/details";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/tickets";
    }
}
