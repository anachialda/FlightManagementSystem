package com.example.flight.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.validation.constraints.NotBlank;

@Entity
@DiscriminatorValue("AIRPORT")
public class AirportEmployee extends Staff {

    @NotBlank
    private String designation;

    @NotBlank
    private String department;

    public AirportEmployee() {
    }

    public AirportEmployee(String name, String designation, String department) {
        super(name);  // let DB handle id
        this.designation = designation;
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}