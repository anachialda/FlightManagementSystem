package com.example.flight.demo.controller;

import com.example.flight.demo.model.AirportEmployee;
import com.example.flight.demo.service.AirportEmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/airport-employees")
public class AirportEmployeeController {
    private final AirportEmployeeService service;

    public AirportEmployeeController(AirportEmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("employees", service.all());
        return "airport-employee/index";
    }

    @GetMapping("/new")
    public String form(Model model){
        model.addAttribute("employee", new AirportEmployee());
        return "airport-employee/form";
    }

    @PostMapping
    public String create(@ModelAttribute AirportEmployee employee){
        employee.setId(null); // auto-ID
        service.save(employee);
        return "redirect:/airport-employees";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        service.delete(id);
        return "redirect:/airport-employees";
    }
}
