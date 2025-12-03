package com.example.flight.demo.controller;

import com.example.flight.demo.model.AirportEmployee;
import com.example.flight.demo.repository.AirportEmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/airport-employees")
public class AirportEmployeeController {

    private final AirportEmployeeRepository repository;

    public AirportEmployeeController(AirportEmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("employees", repository.findAll());
        return "airportEmployees/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("employee", new AirportEmployee());
        return "airportEmployees/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("employee", repository.findById(id).orElseThrow());
        return "airportEmployees/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("employee") AirportEmployee employee, BindingResult result) {
        if (result.hasErrors()) {
            return "airportEmployees/form";
        }
        repository.save(employee);
        return "redirect:/airport-employees";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/airport-employees";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("employee", repository.findById(id).orElseThrow());
        return "airportEmployees/details";
    }
}
