package com.example.flight.demo.controller;

import com.example.flight.demo.model.FlightAssignment;
import com.example.flight.demo.service.FlightAssignmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/assignments")
public class FlightAssignmentController {

    private final FlightAssignmentService service;

    public FlightAssignmentController(FlightAssignmentService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("assignments", service.findAll());
        return "assignments/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("assignment", new FlightAssignment());
        return "assignments/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        FlightAssignment assignment = service.findById(id);
        if (assignment == null) return "redirect:/assignments";
        model.addAttribute("assignment", assignment);
        return "assignments/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute FlightAssignment assignment) {
        service.save(assignment);
        return "redirect:/assignments";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable String id, Model model) {
        FlightAssignment assignment = service.findById(id);
        if (assignment == null) return "redirect:/assignments";
        model.addAttribute("assignment", assignment);
        return "assignments/details";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/assignments";
    }
}
