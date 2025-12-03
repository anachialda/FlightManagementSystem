package com.example.flight.demo.controller;

import com.example.flight.demo.model.Airplane;
import com.example.flight.demo.model.Flight;
import com.example.flight.demo.model.Flight.Status;
import com.example.flight.demo.model.NoticeBoard;
import com.example.flight.demo.service.AirplaneService;
import com.example.flight.demo.service.BusinessException;
import com.example.flight.demo.service.FlightService;
import com.example.flight.demo.service.NoticeBoardService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;
    private final AirplaneService airplaneService;
    private final NoticeBoardService noticeBoardService;

    public FlightController(FlightService flightService,
                            AirplaneService airplaneService,
                            NoticeBoardService noticeBoardService) {
        this.flightService = flightService;
        this.airplaneService = airplaneService;
        this.noticeBoardService = noticeBoardService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("flights", flightService.findAll());
        return "flights/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        Flight flight = new Flight();
        prepareFormModel(model, flight);
        return "flights/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Flight flight = flightService.findById(id);
        if (flight == null) {
            return "redirect:/flights";
        }
        prepareFormModel(model, flight);
        return "flights/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("flight") Flight flight,
                       BindingResult bindingResult,
                       Model model) {

        if (bindingResult.hasErrors()) {
            prepareFormModel(model, flight);
            return "flights/form";
        }

        try {
            flightService.save(flight);
            return "redirect:/flights";
        } catch (BusinessException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            prepareFormModel(model, flight);
            return "flights/form";
        }
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        Flight flight = flightService.findById(id);
        if (flight == null) {
            return "redirect:/flights";
        }
        model.addAttribute("flight", flight);
        return "flights/details";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        try {
            flightService.delete(id);
            return "redirect:/flights";
        } catch (BusinessException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("flights", flightService.findAll());
            return "flights/list";
        }
    }

    private void prepareFormModel(Model model, Flight flight) {
        List<Airplane> airplanes = airplaneService.findAll();
        List<NoticeBoard> noticeBoards = noticeBoardService.findAll();

        model.addAttribute("flight", flight);
        model.addAttribute("airplanes", airplanes);
        model.addAttribute("noticeBoards", noticeBoards);
        model.addAttribute("statuses", Status.values());
    }
}