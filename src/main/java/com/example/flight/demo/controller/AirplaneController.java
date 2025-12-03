package com.example.flight.demo.controller;

import com.example.flight.demo.model.Airplane;
import com.example.flight.demo.service.AirplaneService;
import com.example.flight.demo.service.BusinessException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/airplanes")
public class AirplaneController {

    private final AirplaneService airplaneService;

    public AirplaneController(AirplaneService airplaneService) {
        this.airplaneService = airplaneService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("airplanes", airplaneService.findAll());
        return "airplanes/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("airplane", new Airplane());
        return "airplanes/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Airplane airplane = airplaneService.findById(id);
        if (airplane == null) {
            return "redirect:/airplanes";
        }
        model.addAttribute("airplane", airplane);
        return "airplanes/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("airplane") Airplane airplane,
                       BindingResult bindingResult,
                       Model model) {

        if (bindingResult.hasErrors()) {
            return "airplanes/form";
        }

        try {
            airplaneService.save(airplane);
            return "redirect:/airplanes";
        } catch (BusinessException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "airplanes/form";
        }
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        Airplane airplane = airplaneService.findById(id);
        if (airplane == null) {
            return "redirect:/airplanes";
        }
        model.addAttribute("airplane", airplane);
        return "airplanes/details";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        try {
            airplaneService.delete(id);
            return "redirect:/airplanes";
        } catch (BusinessException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("airplanes", airplaneService.findAll());
            return "airplanes/list";
        }
    }
}