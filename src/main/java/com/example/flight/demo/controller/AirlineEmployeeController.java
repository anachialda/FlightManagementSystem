package com.example.flight.demo.controller;

import com.example.flight.demo.model.AirlineEmployee;
import com.example.flight.demo.service.AirlineEmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/airline-employees")
public class AirlineEmployeeController {
    private final AirlineEmployeeService service;

    public AirlineEmployeeController(AirlineEmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("employees", service.all());
        return "airline-employee/index";
    }

    @GetMapping("/new")
    public String form(Model model){
        model.addAttribute("employee", new AirlineEmployee());
        return "airline-employee/form";
    }

    @PostMapping
    public String create(@ModelAttribute AirlineEmployee employee){
        employee.setId(null); // auto-ID
        service.save(employee);
        return "redirect:/airline-employees";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        service.delete(id);
        return "redirect:/airline-employees";
    }
}