package com.example.flight.demo.controller;

import com.example.flight.demo.model.AirlineEmployee;
import com.example.flight.demo.model.Flight;
import com.example.flight.demo.model.FlightAssignment;
import com.example.flight.demo.repository.AirlineEmployeeRepository;
import com.example.flight.demo.repository.FlightAssignmentRepository;
import com.example.flight.demo.repository.FlightRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/assignments")
public class FlightAssignmentController {

    private final FlightAssignmentRepository assignmentRepository;
    private final FlightRepository flightRepository;
    private final AirlineEmployeeRepository employeeRepository;

    public FlightAssignmentController(FlightAssignmentRepository assignmentRepository,
                                      FlightRepository flightRepository,
                                      AirlineEmployeeRepository employeeRepository) {
        this.assignmentRepository = assignmentRepository;
        this.flightRepository = flightRepository;
        this.employeeRepository = employeeRepository;
    }

    @ModelAttribute("flights")
    public Iterable<Flight> flights() {
        return flightRepository.findAll();
    }

    @ModelAttribute("employees")
    public Iterable<AirlineEmployee> employees() {
        return employeeRepository.findAll();
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("assignments", assignmentRepository.findAll());
        return "assignments/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("assignment", new FlightAssignment());
        return "assignments/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        FlightAssignment assignment = assignmentRepository.findById(id).orElseThrow();
        if (assignment.getFlight() != null) {
            assignment.setFlightId(assignment.getFlight().getId());
        }
        if (assignment.getStaff() != null) {
            assignment.setStaffId(assignment.getStaff().getId());
        }
        model.addAttribute("assignment", assignment);
        return "assignments/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("assignment") FlightAssignment assignment,
                       BindingResult result) {
        if (result.hasErrors()) {
            return "assignments/form";
        }
        if (assignment.getFlightId() != null) {
            flightRepository.findById(assignment.getFlightId()).ifPresent(assignment::setFlight);
        }
        if (assignment.getStaffId() != null) {
            employeeRepository.findById(assignment.getStaffId()).ifPresent(assignment::setStaff);
        }
        assignmentRepository.save(assignment);
        return "redirect:/assignments";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        assignmentRepository.deleteById(id);
        return "redirect:/assignments";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("assignment", assignmentRepository.findById(id).orElseThrow());
        return "assignments/details";
    }
}
