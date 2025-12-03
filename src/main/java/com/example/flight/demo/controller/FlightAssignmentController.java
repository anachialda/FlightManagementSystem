package com.example.flight.demo.controller;

import com.example.flight.demo.model.Flight;
import com.example.flight.demo.model.FlightAssignment;
import com.example.flight.demo.model.Staff;
import com.example.flight.demo.service.BusinessException;
import com.example.flight.demo.service.FlightAssignmentService;
import com.example.flight.demo.service.FlightService;
import com.example.flight.demo.service.StaffService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/flight-assignments")
public class FlightAssignmentController {

    private final FlightAssignmentService flightAssignmentService;
    private final FlightService flightService;
    private final StaffService staffService;

    public FlightAssignmentController(FlightAssignmentService flightAssignmentService,
                                      FlightService flightService,
                                      StaffService staffService) {
        this.flightAssignmentService = flightAssignmentService;
        this.flightService = flightService;
        this.staffService = staffService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("assignments", flightAssignmentService.findAll());
        return "flightAssignments/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        FlightAssignment assignment = new FlightAssignment();
        prepareFormModel(model, assignment);
        return "flightAssignments/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        FlightAssignment assignment = flightAssignmentService.findById(id);
        if (assignment == null) {
            return "redirect:/flight-assignments";
        }
        prepareFormModel(model, assignment);
        return "flightAssignments/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("assignment") FlightAssignment assignment,
                       BindingResult bindingResult,
                       Model model) {

        if (bindingResult.hasErrors()) {
            prepareFormModel(model, assignment);
            return "flightAssignments/form";
        }

        try {
            flightAssignmentService.save(assignment);
            return "redirect:/flight-assignments";
        } catch (BusinessException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            prepareFormModel(model, assignment);
            return "flightAssignments/form";
        }
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        FlightAssignment assignment = flightAssignmentService.findById(id);
        if (assignment == null) {
            return "redirect:/flight-assignments";
        }
        model.addAttribute("assignment", assignment);
        return "flightAssignments/details";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        try {
            flightAssignmentService.delete(id);
            return "redirect:/flight-assignments";
        } catch (BusinessException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("assignments", flightAssignmentService.findAll());
            return "flightAssignments/list";
        }
    }

    private void prepareFormModel(Model model, FlightAssignment assignment) {
        List<Flight> flights = flightService.findAll();
        List<Staff> staffList = staffService.findAll();
        model.addAttribute("assignment", assignment);
        model.addAttribute("flights", flights);
        model.addAttribute("staffList", staffList);
    }
}