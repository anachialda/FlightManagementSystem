package com.example.flight.demo.controller;

import com.example.flight.demo.model.*;
import com.example.flight.demo.repository.AirplaneRepository;
import com.example.flight.demo.repository.FlightRepository;
import com.example.flight.demo.repository.NoticeBoardRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/flights")
public class FlightController {

    private final FlightRepository flightRepository;
    private final AirplaneRepository airplaneRepository;
    private final NoticeBoardRepository noticeBoardRepository;

    public FlightController(FlightRepository flightRepository,
                            AirplaneRepository airplaneRepository,
                            NoticeBoardRepository noticeBoardRepository) {
        this.flightRepository = flightRepository;
        this.airplaneRepository = airplaneRepository;
        this.noticeBoardRepository = noticeBoardRepository;
    }

    @ModelAttribute("statuses")
    public FlightStatus[] statuses() {
        return FlightStatus.values();
    }

    @ModelAttribute("noticeBoards")
    public Iterable<NoticeBoard> noticeBoards() {
        return noticeBoardRepository.findAll();
    }

    @ModelAttribute("airplanes")
    public Iterable<Airplane> airplanes() {
        return airplaneRepository.findAll();
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("flights", flightRepository.findAll());
        return "flights/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("flight", new Flight());
        return "flights/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Flight flight = flightRepository.findById(id).orElseThrow();
        if (flight.getNoticeBoard() != null) {
            flight.setNoticeBoardId(flight.getNoticeBoard().getId());
        }
        if (flight.getAirplane() != null) {
            flight.setAirplaneId(flight.getAirplane().getId());
        }
        model.addAttribute("flight", flight);
        return "flights/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("flight") Flight flight,
                       BindingResult result) {
        if (result.hasErrors()) {
            return "flights/form";
        }

        if (flight.getNoticeBoardId() != null) {
            noticeBoardRepository.findById(flight.getNoticeBoardId()).ifPresent(flight::setNoticeBoard);
        } else {
            flight.setNoticeBoard(null);
        }
        if (flight.getAirplaneId() != null) {
            airplaneRepository.findById(flight.getAirplaneId()).ifPresent(flight::setAirplane);
        } else {
            flight.setAirplane(null);
        }

        flightRepository.save(flight);
        return "redirect:/flights";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        flightRepository.deleteById(id);
        return "redirect:/flights";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("flight", flightRepository.findById(id).orElseThrow());
        return "flights/details";
    }
}
