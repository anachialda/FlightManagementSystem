package com.example.flight.demo.controller;

import com.example.flight.demo.model.Airplane;
import com.example.flight.demo.service.AirplaneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/airplanes")
public class AirplaneController {
    private final AirplaneService service;
    public AirplaneController(AirplaneService service){ this.service = service; }

    @GetMapping
    public String index(Model model){
        model.addAttribute("airplanes", service.all());
        return "airplane/index";
    }

    @GetMapping("/new")
    public String form(Model model){
        model.addAttribute("airplane", new Airplane());
        return "airplane/form";
    }

    @PostMapping
    public String create(@ModelAttribute Airplane airplane){
        service.save(airplane);
        return "redirect:/airplanes";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        service.delete(id);
        return "redirect:/airplanes";
    }
}