package com.example.flight.demo.service;

import com.example.flight.demo.model.AirlineEmployee;
import com.example.flight.demo.model.FlightAssignment;
import com.example.flight.demo.repository.AirlineEmployeeRepository;
import com.example.flight.demo.repository.FlightAssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineEmployeeService {

    private final AirlineEmployeeRepository airlineEmployeeRepository;
    private final FlightAssignmentRepository flightAssignmentRepository;

    public AirlineEmployeeService(AirlineEmployeeRepository airlineEmployeeRepository,
                                  FlightAssignmentRepository flightAssignmentRepository) {
        this.airlineEmployeeRepository = airlineEmployeeRepository;
        this.flightAssignmentRepository = flightAssignmentRepository;
    }

    public List<AirlineEmployee> findAll() {
        return airlineEmployeeRepository.findAll();
    }

    public AirlineEmployee findById(Long id) {
        return airlineEmployeeRepository.findById(id).orElse(null);
    }

    public AirlineEmployee save(AirlineEmployee employee) {
        boolean isNew = (employee.getId() == null);

        // Unicitate licenseNumber
        airlineEmployeeRepository.findAll().stream()
                .filter(e -> !e.getId().equals(employee.getId()))
                .filter(e -> e.getLicenseNumber().equalsIgnoreCase(employee.getLicenseNumber()))
                .findAny()
                .ifPresent(e -> {
                    throw new BusinessException("Lizenznummer '" + employee.getLicenseNumber() + "' ist bereits vergeben.");
                });

        // Alte reguli de business pot fi adăugate aici
        return airlineEmployeeRepository.save(employee);
    }

    public void delete(Long id) {
        AirlineEmployee employee = airlineEmployeeRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Mitarbeiter mit ID " + id + " wurde nicht gefunden."));

        // Nu șterge dacă are FlightAssignments
        boolean hasAssignments = flightAssignmentRepository.findAll().stream()
                .anyMatch(a -> a.getStaff() != null && id.equals(a.getStaff().getId()));

        if (hasAssignments) {
            throw new BusinessException("Mitarbeiter kann nicht gelöscht werden, da er noch Flugzuweisungen hat.");
        }

        airlineEmployeeRepository.delete(employee);
    }
}