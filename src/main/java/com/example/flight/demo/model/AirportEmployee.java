package com.example.flight.demo.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@DiscriminatorValue("AIRPORT")
public class AirportEmployee extends Staff {

    @NotBlank(message = "Bezeichnung darf nicht leer sein")
    @Size(min = 2, max = 100, message = "Bezeichnung muss zwischen 2 und 100 Zeichen lang sein")
    @Column(nullable = false, length = 100)
    private String designation;

    @NotBlank(message = "Abteilung darf nicht leer sein")
    @Size(min = 2, max = 100, message = "Abteilung muss zwischen 2 und 100 Zeichen lang sein")
    @Column(nullable = false, length = 100)
    private String department;

    public AirportEmployee() {
    }

    public AirportEmployee(String name, String designation, String department) {
        super(name);
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