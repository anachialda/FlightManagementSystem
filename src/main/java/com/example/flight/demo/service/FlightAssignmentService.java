package com.example.flight.demo.service;

import com.example.flight.demo.model.Flight;
import com.example.flight.demo.model.FlightAssignment;
import com.example.flight.demo.model.Staff;
import com.example.flight.demo.repository.FlightAssignmentRepository;
import com.example.flight.demo.repository.FlightRepository;
import com.example.flight.demo.repository.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightAssignmentService {

    private final FlightAssignmentRepository flightAssignmentRepository;
    private final FlightRepository flightRepository;
    private final StaffRepository staffRepository;

    public FlightAssignmentService(FlightAssignmentRepository flightAssignmentRepository,
                                   FlightRepository flightRepository,
                                   StaffRepository staffRepository) {
        this.flightAssignmentRepository = flightAssignmentRepository;
        this.flightRepository = flightRepository;
        this.staffRepository = staffRepository;
    }

    public List<FlightAssignment> findAll() {
        return flightAssignmentRepository.findAll();
    }

    public FlightAssignment findById(Long id) {
        return flightAssignmentRepository.findById(id).orElse(null);
    }

    public FlightAssignment save(FlightAssignment assignment) {
        Flight flight = assignment.getFlight();
        Staff staff = assignment.getStaff();

        if (flight == null || flight.getId() == null ||
                flightRepository.findById(flight.getId()).isEmpty()) {
            throw new BusinessException("Ausgewählter Flug existiert nicht.");
        }

        if (staff == null || staff.getId() == null ||
                staffRepository.findById(staff.getId()).isEmpty()) {
            throw new BusinessException("Ausgewählter Mitarbeiter existiert nicht.");
        }

        Long id = assignment.getId();
        Long flightId = flight.getId();
        Long staffId = staff.getId();

        // Verhindern von Duplikaten: gleicher Mitarbeiter auf gleichem Flug mehrmals
        flightAssignmentRepository.findAll().stream()
                .filter(a -> !a.getId().equals(id))
                .filter(a -> a.getFlight() != null && a.getFlight().getId().equals(flightId))
                .filter(a -> a.getStaff() != null && a.getStaff().getId().equals(staffId))
                .findAny()
                .ifPresent(a -> {
                    throw new BusinessException("Mitarbeiter ist diesem Flug bereits zugeordnet.");
                });

        return flightAssignmentRepository.save(assignment);
    }

    public void delete(Long id) {
        flightAssignmentRepository.deleteById(id);
    }
}