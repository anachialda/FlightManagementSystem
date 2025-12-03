package com.example.flight.demo.controller;

import com.example.flight.demo.model.Passenger;
import com.example.flight.demo.repository.PassengerRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/passengers")
public class PassengerController {

    private final PassengerRepository passengerRepository;

    public PassengerController(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("passengers", passengerRepository.findAll());
        return "passengers/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("passenger", new Passenger());
        return "passengers/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("passenger", passengerRepository.findById(id).orElseThrow());
        return "passengers/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("passenger") Passenger passenger, BindingResult result) {
        if (result.hasErrors()) {
            return "passengers/form";
        }
        passengerRepository.save(passenger);
        return "redirect:/passengers";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        passengerRepository.deleteById(id);
        return "redirect:/passengers";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("passenger", passengerRepository.findById(id).orElseThrow());
        return "passengers/details";
    }
}
