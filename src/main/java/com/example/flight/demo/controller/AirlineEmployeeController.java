package com.example.flight.demo.controller;

import com.example.flight.demo.model.AirlineEmployee;
import com.example.flight.demo.model.AirlineRole;
import com.example.flight.demo.repository.AirlineEmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("/airline-employees")
public class AirlineEmployeeController {

    private final AirlineEmployeeRepository repository;

    public AirlineEmployeeController(AirlineEmployeeRepository repository) {
        this.repository = repository;
    }

    @ModelAttribute("roles")
    public AirlineRole[] roles() {
        return AirlineRole.values();
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("employees", repository.findAll());
        return "airlineEmployees/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("employee", new AirlineEmployee());
        return "airlineEmployees/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("employee", repository.findById(id).orElseThrow());
        return "airlineEmployees/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("employee") AirlineEmployee employee, BindingResult result) {
        if (result.hasErrors()) {
            return "airlineEmployees/form";
        }
        repository.save(employee);
        return "redirect:/airline-employees";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/airline-employees";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("employee", repository.findById(id).orElseThrow());
        return "airlineEmployees/details";
    }
}
