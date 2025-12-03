package com.example.flight.demo.controller;

import com.example.flight.demo.model.Flight;
import com.example.flight.demo.model.Passenger;
import com.example.flight.demo.model.Ticket;
import com.example.flight.demo.repository.FlightRepository;
import com.example.flight.demo.repository.PassengerRepository;
import com.example.flight.demo.repository.TicketRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    private final TicketRepository ticketRepository;
    private final PassengerRepository passengerRepository;
    private final FlightRepository flightRepository;

    public TicketController(TicketRepository ticketRepository,
                            PassengerRepository passengerRepository,
                            FlightRepository flightRepository) {
        this.ticketRepository = ticketRepository;
        this.passengerRepository = passengerRepository;
        this.flightRepository = flightRepository;
    }

    @ModelAttribute("passengers")
    public Iterable<Passenger> passengers() {
        return passengerRepository.findAll();
    }

    @ModelAttribute("flights")
    public Iterable<Flight> flights() {
        return flightRepository.findAll();
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("tickets", ticketRepository.findAll());
        return "tickets/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "tickets/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow();
        if (ticket.getPassenger() != null) {
            ticket.setPassengerId(ticket.getPassenger().getId());
        }
        if (ticket.getFlight() != null) {
            ticket.setFlightId(ticket.getFlight().getId());
        }
        model.addAttribute("ticket", ticket);
        return "tickets/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("ticket") Ticket ticket,
                       BindingResult result) {
        if (result.hasErrors()) {
            return "tickets/form";
        }

        if (ticket.getPassengerId() != null) {
            passengerRepository.findById(ticket.getPassengerId()).ifPresent(ticket::setPassenger);
        }
        if (ticket.getFlightId() != null) {
            flightRepository.findById(ticket.getFlightId()).ifPresent(ticket::setFlight);
        }

        ticketRepository.save(ticket);
        return "redirect:/tickets";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        ticketRepository.deleteById(id);
        return "redirect:/tickets";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("ticket", ticketRepository.findById(id).orElseThrow());
        return "tickets/details";
    }
}
