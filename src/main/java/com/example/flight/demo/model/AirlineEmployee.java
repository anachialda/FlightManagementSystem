package com.example.flight.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

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

    @NotNull(message = "Rolle darf nicht leer sein")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private Role role;

    @NotBlank(message = "Lizenznummer darf nicht leer sein")
    @Size(min = 3, max = 50, message = "Lizenznummer muss zwischen 3 und 50 Zeichen lang sein")
    @Column(nullable = false, length = 50, unique = true)
    private String licenseNumber;

    @PastOrPresent(message = "Arbeitsbeginn darf nicht in der Zukunft liegen")
    private LocalDate workStart;

    @OneToMany(mappedBy = "staff", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FlightAssignment> assignments = new ArrayList<>();

    public AirlineEmployee() {
    }

    public AirlineEmployee(String name, Role role, String licenseNumber) {
        super(name);
        this.role = role;
        this.licenseNumber = licenseNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public List<FlightAssignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<FlightAssignment> assignments) {
        this.assignments = assignments;
    }
}