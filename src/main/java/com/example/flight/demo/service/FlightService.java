package com.example.flight.demo.service;

import com.example.flight.demo.model.Airplane;
import com.example.flight.demo.model.Flight;
import com.example.flight.demo.model.NoticeBoard;
import com.example.flight.demo.repository.AirplaneRepository;
import com.example.flight.demo.repository.FlightRepository;
import com.example.flight.demo.repository.NoticeBoardRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirplaneRepository airplaneRepository;
    private final NoticeBoardRepository noticeBoardRepository;

    public FlightService(FlightRepository flightRepository,
                         AirplaneRepository airplaneRepository,
                         NoticeBoardRepository noticeBoardRepository) {
        this.flightRepository = flightRepository;
        this.airplaneRepository = airplaneRepository;
        this.noticeBoardRepository = noticeBoardRepository;
    }

    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    public Flight findById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    public Flight save(Flight flight) {
        validateFlightBusinessRules(flight);
        return flightRepository.save(flight);
    }

    private void validateFlightBusinessRules(Flight flight) {
        LocalDateTime dep = flight.getDepartureTime();
        LocalDateTime arr = flight.getArrivalTime();

        if (dep != null && arr != null && !arr.isAfter(dep)) {
            throw new BusinessException("Ankunftszeit muss nach der Abflugzeit liegen.");
        }

        // Flugzeug existent?
        Airplane airplane = flight.getAirplane();
        if (airplane == null || airplane.getId() == null ||
                airplaneRepository.findById(airplane.getId()).isEmpty()) {
            throw new BusinessException("Das ausgewählte Flugzeug existiert nicht.");
        }

        // NoticeBoard, dacă e setat, trebuie să existe
        NoticeBoard nb = flight.getNoticeBoard();
        if (nb != null && nb.getId() != null &&
                noticeBoardRepository.findById(nb.getId()).isEmpty()) {
            throw new BusinessException("Das ausgewählte NoticeBoard existiert nicht.");
        }
    }

    public void delete(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Flug mit ID " + id + " wurde nicht gefunden."));

        if (!flight.getTickets().isEmpty()) {
            throw new BusinessException("Flug kann nicht gelöscht werden, da noch Tickets vorhanden sind.");
        }

        if (!flight.getFlightAssignments().isEmpty()) {
            throw new BusinessException("Flug kann nicht gelöscht werden, da noch Mitarbeiter zugewiesen sind.");
        }

        flightRepository.delete(flight);
    }
}