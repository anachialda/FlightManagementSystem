package com.example.flight.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "staff")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "staff_type")
public abstract class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;          // now DB-generated primary key

    @NotBlank
    private String name;


    public Staff() {
    }

    // convenience constructor without id (DB will generate it)
    public Staff(String name) {
        this.name = name;
    }

    // --- getters & setters ---

    public Long getId() {
        return id;
    }

    // usually no setter for id needed, but you can keep it if you want
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}