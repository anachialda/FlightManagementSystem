package com.example.flight.demo.controller;

import com.example.flight.demo.model.Flight;
import com.example.flight.demo.model.Passenger;
import com.example.flight.demo.model.Ticket;
import com.example.flight.demo.service.BusinessException;
import com.example.flight.demo.service.FlightService;
import com.example.flight.demo.service.PassengerService;
import com.example.flight.demo.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final PassengerService passengerService;
    private final FlightService flightService;

    public TicketController(TicketService ticketService,
                            PassengerService passengerService,
                            FlightService flightService) {
        this.ticketService = ticketService;
        this.passengerService = passengerService;
        this.flightService = flightService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("tickets", ticketService.findAll());
        return "tickets/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        Ticket ticket = new Ticket();
        prepareFormModel(model, ticket);
        return "tickets/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Ticket ticket = ticketService.findById(id);
        if (ticket == null) {
            return "redirect:/tickets";
        }
        prepareFormModel(model, ticket);
        return "tickets/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("ticket") Ticket ticket,
                       BindingResult bindingResult,
                       Model model) {

        if (bindingResult.hasErrors()) {
            prepareFormModel(model, ticket);
            return "tickets/form";
        }

        try {
            ticketService.save(ticket);
            return "redirect:/tickets";
        } catch (BusinessException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            prepareFormModel(model, ticket);
            return "tickets/form";
        }
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        Ticket ticket = ticketService.findById(id);
        if (ticket == null) {
            return "redirect:/tickets";
        }
        model.addAttribute("ticket", ticket);
        return "tickets/details";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        try {
            ticketService.delete(id);
            return "redirect:/tickets";
        } catch (BusinessException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("tickets", ticketService.findAll());
            return "tickets/list";
        }
    }

    private void prepareFormModel(Model model, Ticket ticket) {
        List<Passenger> passengers = passengerService.findAll();
        List<Flight> flights = flightService.findAll();

        model.addAttribute("ticket", ticket);
        model.addAttribute("passengers", passengers);
        model.addAttribute("flights", flights);
    }
}