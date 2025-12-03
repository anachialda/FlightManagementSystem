package com.example.flight.demo.service;

import com.example.flight.demo.model.AirportEmployee;
import com.example.flight.demo.repository.AirportEmployeeRepository;
import com.example.flight.demo.repository.FlightAssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportEmployeeService {

    private final AirportEmployeeRepository airportEmployeeRepository;
    private final FlightAssignmentRepository flightAssignmentRepository;

    public AirportEmployeeService(AirportEmployeeRepository airportEmployeeRepository,
                                  FlightAssignmentRepository flightAssignmentRepository) {
        this.airportEmployeeRepository = airportEmployeeRepository;
        this.flightAssignmentRepository = flightAssignmentRepository;
    }

    public List<AirportEmployee> findAll() {
        return airportEmployeeRepository.findAll();
    }

    public AirportEmployee findById(Long id) {
        return airportEmployeeRepository.findById(id).orElse(null);
    }

    public AirportEmployee save(AirportEmployee employee) {
        // aici momentan nu avem câmpuri unice extra → JSR-303 se ocupă de restul
        return airportEmployeeRepository.save(employee);
    }

    public void delete(Long id) {
        AirportEmployee employee = airportEmployeeRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Mitarbeiter mit ID " + id + " wurde nicht gefunden."));

        boolean hasAssignments = flightAssignmentRepository.findAll().stream()
                .anyMatch(a -> a.getStaff() != null && id.equals(a.getStaff().getId()));

        if (hasAssignments) {
            throw new BusinessException("Mitarbeiter kann nicht gelöscht werden, da er noch Flugzuweisungen hat.");
        }

        airportEmployeeRepository.delete(employee);
    }
}