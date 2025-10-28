package com.example.flight.demo.model;

public class AirportEmployee extends Staff {
    private String designation;
    private String department;

    public AirportEmployee() {}
    public AirportEmployee(Long id, String name, String designation, String department) {
        super(id, name);
        this.designation = designation;
        this.department = department;
    }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}