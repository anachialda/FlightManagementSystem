package com.example.flight.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "staff")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "staff_type")
public abstract class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // PK generat de DB

    @NotBlank(message = "Name darf nicht leer sein")
    @Size(min = 2, max = 100, message = "Name muss zwischen 2 und 100 Zeichen lang sein")
    @Column(nullable = false, length = 100)
    private String name;

    public Staff() { }

    public Staff(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

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