package com.example.flight.demo.controller;

import com.example.flight.demo.model.Passenger;
import com.example.flight.demo.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/passengers")
public class PassengerController {

    private final PassengerService service;

    public PassengerController(PassengerService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("passengers", service.findAll());
        return "passengers/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("passenger", new Passenger());
        return "passengers/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Passenger passenger = service.findById(id);
        if (passenger == null) {
            return "redirect:/passengers";
        }
        model.addAttribute("passenger", passenger);
        return "passengers/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("passenger") Passenger passenger,
                       BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "passengers/form";
        }

        service.save(passenger);
        return "redirect:/passengers";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        Passenger passenger = service.findById(id);
        if (passenger == null) {
            return "redirect:/passengers";
        }
        model.addAttribute("passenger", passenger);
        return "passengers/details";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/passengers";
    }
}