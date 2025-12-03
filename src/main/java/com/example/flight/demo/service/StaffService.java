package com.example.flight.demo.service;

import com.example.flight.demo.model.Staff;
import com.example.flight.demo.repository.FlightAssignmentRepository;
import com.example.flight.demo.repository.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {

    private final StaffRepository staffRepository;
    private final FlightAssignmentRepository flightAssignmentRepository;

    public StaffService(StaffRepository staffRepository,
                        FlightAssignmentRepository flightAssignmentRepository) {
        this.staffRepository = staffRepository;
        this.flightAssignmentRepository = flightAssignmentRepository;
    }

    public List<Staff> findAll() {
        return staffRepository.findAll();
    }

    public Staff findById(Long id) {
        return staffRepository.findById(id).orElse(null);
    }

    public Staff save(Staff staff) {
        return staffRepository.save(staff);
    }

    public void delete(Long id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Mitarbeiter mit ID " + id + " wurde nicht gefunden."));

        boolean hasAssignments = flightAssignmentRepository.findAll().stream()
                .anyMatch(a -> a.getStaff() != null && id.equals(a.getStaff().getId()));

        if (hasAssignments) {
            throw new BusinessException("Mitarbeiter kann nicht gelöscht werden, da er noch Flugzuweisungen hat.");
        }

        staffRepository.delete(staff);
    }
}