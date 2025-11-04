package com.example.flight.demo.controller;

import com.example.flight.demo.model.Passenger;
import com.example.flight.demo.service.PassengerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/passengers")
public class PassengerController {
    private final PassengerService service;
    public PassengerController(PassengerService service){ this.service = service; }

    @GetMapping
    public String index(Model model){
        model.addAttribute("passengers", service.all());
        return "passenger/index";
    }

    @GetMapping("/new")
    public String form(Model model){
        model.addAttribute("passenger", new Passenger());
        return "passenger/form";
    }

    @PostMapping
    public String create(@ModelAttribute Passenger passenger){
        service.save(passenger);
        return "redirect:/passengers";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        service.delete(id);
        return "redirect:/passengers";
    }
}