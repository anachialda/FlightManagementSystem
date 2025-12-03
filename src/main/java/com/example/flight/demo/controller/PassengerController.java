package com.example.flight.demo.controller;

import com.example.flight.demo.model.Passenger;
import com.example.flight.demo.service.BusinessException;
import com.example.flight.demo.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/passengers")
public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("passengers", passengerService.findAll());
        return "passengers/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("passenger", new Passenger());
        return "passengers/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Passenger passenger = passengerService.findById(id);
        if (passenger == null) {
            return "redirect:/passengers";
        }
        model.addAttribute("passenger", passenger);
        return "passengers/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("passenger") Passenger passenger,
                       BindingResult bindingResult,
                       Model model) {

        if (bindingResult.hasErrors()) {
            return "passengers/form";
        }

        try {
            passengerService.save(passenger);
            return "redirect:/passengers";
        } catch (BusinessException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "passengers/form";
        }
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        Passenger passenger = passengerService.findById(id);
        if (passenger == null) {
            return "redirect:/passengers";
        }
        model.addAttribute("passenger", passenger);
        return "passengers/details";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        try {
            passengerService.delete(id);
            return "redirect:/passengers";
        } catch (BusinessException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("passengers", passengerService.findAll());
            return "passengers/list";
        }
    }
}