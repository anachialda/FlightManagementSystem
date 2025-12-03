package com.example.flight.demo.controller;

import com.example.flight.demo.model.Luggage;
import com.example.flight.demo.model.LuggageStatus;
import com.example.flight.demo.repository.LuggageRepository;
import com.example.flight.demo.repository.TicketRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/luggage")
public class LuggageController {

    private final LuggageRepository luggageRepository;
    private final TicketRepository ticketRepository;

    public LuggageController(LuggageRepository luggageRepository, TicketRepository ticketRepository) {
        this.luggageRepository = luggageRepository;
        this.ticketRepository = ticketRepository;
    }

    @ModelAttribute("statuses")
    public LuggageStatus[] statuses() {
        return LuggageStatus.values();
    }

    @ModelAttribute("tickets")
    public Iterable<Ticket> tickets() {
        return ticketRepository.findAll();
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("luggageList", luggageRepository.findAll());
        return "luggage/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("luggage", new Luggage());
        return "luggage/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Luggage luggage = luggageRepository.findById(id).orElseThrow();
        if (luggage.getTicket() != null) {
            luggage.setTicketId(luggage.getTicket().getId());
        }
        model.addAttribute("luggage", luggage);
        return "luggage/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("luggage") Luggage luggage,
                       BindingResult result) {
        if (result.hasErrors()) {
            return "luggage/form";
        }
        if (luggage.getTicketId() != null) {
            ticketRepository.findById(luggage.getTicketId()).ifPresent(luggage::setTicket);
        }
        luggageRepository.save(luggage);
        return "redirect:/luggage";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        luggageRepository.deleteById(id);
        return "redirect:/luggage";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("luggage", luggageRepository.findById(id).orElseThrow());
        return "luggage/details";
    }
}
