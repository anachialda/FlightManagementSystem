package com.example.flight.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AirlineEmployee extends Staff {
    private String role; // enum
    private List<Long> assignments = new ArrayList<>();
    private String licenseNumber;
    private LocalDate workStart;

    public AirlineEmployee() {}
    public AirlineEmployee(Long id, String name, String role) {
        super(id, name);
        this.role = role;
    }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public List<Long> getAssignments() { return assignments; }
    public void setAssignments(List<Long> assignments) { this.assignments = assignments; }
    public String getLicenseNumber() { return licenseNumber; }
    public void setLicenseNumber(String licenseNumber) { this.licenseNumber = licenseNumber; }
    public LocalDate getWorkStart() { return workStart; }
    public void setWorkStart(LocalDate workStart) { this.workStart = workStart; }
}