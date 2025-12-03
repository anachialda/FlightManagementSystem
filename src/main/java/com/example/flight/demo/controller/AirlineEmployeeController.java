package com.example.flight.demo.controller;

import com.example.flight.demo.model.AirlineEmployee;
import com.example.flight.demo.model.AirlineEmployee.Role;
import com.example.flight.demo.service.AirlineEmployeeService;
import com.example.flight.demo.service.BusinessException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/airline-employees")
public class AirlineEmployeeController {

    private final AirlineEmployeeService airlineEmployeeService;

    public AirlineEmployeeController(AirlineEmployeeService airlineEmployeeService) {
        this.airlineEmployeeService = airlineEmployeeService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("employees", airlineEmployeeService.findAll());
        return "airlineEmployees/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("employee", new AirlineEmployee());
        model.addAttribute("roles", Role.values());
        return "airlineEmployees/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        AirlineEmployee employee = airlineEmployeeService.findById(id);
        if (employee == null) {
            return "redirect:/airline-employees";
        }
        model.addAttribute("employee", employee);
        model.addAttribute("roles", Role.values());
        return "airlineEmployees/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("employee") AirlineEmployee employee,
                       BindingResult bindingResult,
                       Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", Role.values());
            return "airlineEmployees/form";
        }

        try {
            airlineEmployeeService.save(employee);
            return "redirect:/airline-employees";
        } catch (BusinessException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("roles", Role.values());
            return "airlineEmployees/form";
        }
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        AirlineEmployee employee = airlineEmployeeService.findById(id);
        if (employee == null) {
            return "redirect:/airline-employees";
        }
        model.addAttribute("employee", employee);
        return "airlineEmployees/details";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        try {
            airlineEmployeeService.delete(id);
            return "redirect:/airline-employees";
        } catch (BusinessException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("employees", airlineEmployeeService.findAll());
            return "airlineEmployees/list";
        }
    }
}