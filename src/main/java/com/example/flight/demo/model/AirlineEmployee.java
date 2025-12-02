package com.example.flight.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("AIRLINE")
public class AirlineEmployee extends Staff {

    public enum Role {
        PILOT,
        COPILOT,
        FLIGHT_ATTENDANT,
        ENGINEER,
        MECHANIC,
        GROUND_STAFF
    }

    @Enumerated(EnumType.STRING)
    private Role role;

    // Store IDs of FlightAssignment as String → we keep that, but now map it as a separate table
    @ElementCollection
    @CollectionTable(
            name = "airline_employee_assignments",
            joinColumns = @JoinColumn(name = "airline_employee_id")
    )
    @Column(name = "assignment_id")
    private List<String> assignments = new ArrayList<>();

    private String licenseNumber;

    private LocalDate workStart;

    public AirlineEmployee() {
    }

    public AirlineEmployee(String name, Role role) {
        super(name);
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<String> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<String> assignments) {
        this.assignments = assignments;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public LocalDate getWorkStart() {
        return workStart;
    }

    public void setWorkStart(LocalDate workStart) {
        this.workStart = workStart;
    }
}