package com.example.flight.demo.controller;

import com.example.flight.demo.model.Ticket;
import com.example.flight.demo.repository.TicketRepository;
import com.example.flight.demo.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService service;
    private final TicketRepository repo; // for delete

    public TicketController(TicketService service, TicketRepository repo) {
        this.service = service;
        this.repo = repo;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("tickets", service.all());
        return "ticket/index";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "ticket/form";
    }

    @PostMapping
    public String create(
            @RequestParam Long passengerId,
            @RequestParam Long flightId,
            @RequestParam String seatNumber,
            @RequestParam Double price
    ) {
        Ticket t = new Ticket(null, passengerId, flightId, price, seatNumber);
        //service.issue(t); // enforces seat uniqueness
        return "redirect:/tickets";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        repo.delete(id);
        return "redirect:/tickets";
    }
}

