package com.example.flight.demo.controller;

import com.example.flight.demo.model.Luggage;
import com.example.flight.demo.model.Luggage.Status;
import com.example.flight.demo.model.Ticket;
import com.example.flight.demo.service.BusinessException;
import com.example.flight.demo.service.LuggageService;
import com.example.flight.demo.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/luggages")
public class LuggageController {

    private final LuggageService luggageService;
    private final TicketService ticketService;

    public LuggageController(LuggageService luggageService, TicketService ticketService) {
        this.luggageService = luggageService;
        this.ticketService = ticketService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("luggages", luggageService.findAll());
        return "luggages/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        Luggage luggage = new Luggage();
        prepareFormModel(model, luggage);
        return "luggages/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Luggage luggage = luggageService.findById(id);
        if (luggage == null) {
            return "redirect:/luggages";
        }
        prepareFormModel(model, luggage);
        return "luggages/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("luggage") Luggage luggage,
                       BindingResult bindingResult,
                       Model model) {

        if (bindingResult.hasErrors()) {
            prepareFormModel(model, luggage);
            return "luggages/form";
        }

        try {
            luggageService.save(luggage);
            return "redirect:/luggages";
        } catch (BusinessException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            prepareFormModel(model, luggage);
            return "luggages/form";
        }
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        Luggage luggage = luggageService.findById(id);
        if (luggage == null) {
            return "redirect:/luggages";
        }
        model.addAttribute("luggage", luggage);
        return "luggages/details";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        try {
            luggageService.delete(id);
            return "redirect:/luggages";
        } catch (BusinessException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("luggages", luggageService.findAll());
            return "luggages/list";
        }
    }

    private void prepareFormModel(Model model, Luggage luggage) {
        List<Ticket> tickets = ticketService.findAll();
        model.addAttribute("luggage", luggage);
        model.addAttribute("tickets", tickets);
        model.addAttribute("statuses", Status.values());
    }
}