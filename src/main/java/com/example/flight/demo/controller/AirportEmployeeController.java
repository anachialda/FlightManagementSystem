package com.example.flight.demo.controller;

import com.example.flight.demo.model.AirportEmployee;
import com.example.flight.demo.service.AirportEmployeeService;
import com.example.flight.demo.service.BusinessException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/airport-employees")
public class AirportEmployeeController {

    private final AirportEmployeeService airportEmployeeService;

    public AirportEmployeeController(AirportEmployeeService airportEmployeeService) {
        this.airportEmployeeService = airportEmployeeService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("employees", airportEmployeeService.findAll());
        return "airportEmployees/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new AirportEmployee());
        return "airportEmployees/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        AirportEmployee employee = airportEmployeeService.findById(id);
        if (employee == null) {
            return "redirect:/airport-employees";
        }
        model.addAttribute("employee", employee);
        return "airportEmployees/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("employee") AirportEmployee employee,
                       BindingResult bindingResult,
                       Model model) {

        if (bindingResult.hasErrors()) {
            return "airportEmployees/form";
        }

        try {
            airportEmployeeService.save(employee);
            return "redirect:/airport-employees";
        } catch (BusinessException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "airportEmployees/form";
        }
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        AirportEmployee employee = airportEmployeeService.findById(id);
        if (employee == null) {
            return "redirect:/airport-employees";
        }
        model.addAttribute("employee", employee);
        return "airportEmployees/details";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        try {
            airportEmployeeService.delete(id);
            return "redirect:/airport-employees";
        } catch (BusinessException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("employees", airportEmployeeService.findAll());
            return "airportEmployees/list";
        }
    }
}