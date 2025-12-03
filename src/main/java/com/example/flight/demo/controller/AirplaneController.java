package com.example.flight.demo.controller;

import com.example.flight.demo.model.Airplane;
import com.example.flight.demo.repository.AirplaneRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/airplanes")
public class AirplaneController {

    private final AirplaneRepository airplaneRepository;

    public AirplaneController(AirplaneRepository airplaneRepository) {
        this.airplaneRepository = airplaneRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("airplanes", airplaneRepository.findAll());
        return "airplanes/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("airplane", new Airplane());
        return "airplanes/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("airplane", airplaneRepository.findById(id).orElseThrow());
        return "airplanes/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("airplane") Airplane airplane, BindingResult result) {
        if (result.hasErrors()) {
            return "airplanes/form";
        }
        airplaneRepository.save(airplane);
        return "redirect:/airplanes";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        airplaneRepository.deleteById(id);
        return "redirect:/airplanes";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("airplane", airplaneRepository.findById(id).orElseThrow());
        return "airplanes/details";
    }
}
