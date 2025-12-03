package com.example.flight.demo.controller;

import com.example.flight.demo.model.AirportEmployee;
import com.example.flight.demo.service.AirportEmployeeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/airport-employees")
public class AirportEmployeeController {

    private final AirportEmployeeService service;

    public AirportEmployeeController(AirportEmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("employees", service.findAll());
        return "airportEmployees/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new AirportEmployee());
        return "airportEmployees/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        AirportEmployee employee = service.findById(id);
        if (employee == null) {
            return "redirect:/airport-employees";
        }
        model.addAttribute("employee", employee);
        return "airportEmployees/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("employee") AirportEmployee employee,
                       BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "airportEmployees/form";
        }

        service.save(employee);
        return "redirect:/airport-employees";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        AirportEmployee employee = service.findById(id);
        if (employee == null) {
            return "redirect:/airport-employees";
        }
        model.addAttribute("employee", employee);
        return "airportEmployees/details";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/airport-employees";
    }
}