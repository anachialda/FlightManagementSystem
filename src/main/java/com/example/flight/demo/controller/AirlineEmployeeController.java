package com.example.flight.demo.controller;

import com.example.flight.demo.model.AirlineEmployee;
import com.example.flight.demo.service.AirlineEmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/airline-employees")
public class AirlineEmployeeController {

    private final AirlineEmployeeService service;

    public AirlineEmployeeController(AirlineEmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("employees", service.all());
        return "airline-employee/index";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("employee", new AirlineEmployee());
        return "airline-employee/form";
    }

    @PostMapping
    public String create(
            @RequestParam String name,
            @RequestParam AirlineEmployee.Role role,
            @RequestParam(required = false) String licenseNumber,
            @RequestParam(required = false) String workStart,          // yyyy-MM-dd
            @RequestParam(required = false, name = "assignments") String assignmentsCv
    ) {
        AirlineEmployee e = new AirlineEmployee();
        e.setId(null);
        e.setName(name);
        e.setRole(role);
        e.setLicenseNumber(licenseNumber);

        if (workStart != null && !workStart.isBlank()) {
            e.setWorkStart(LocalDate.parse(workStart));
        }

        service.save(e);
        return "redirect:/airline-employees";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/airline-employees";
    }
}