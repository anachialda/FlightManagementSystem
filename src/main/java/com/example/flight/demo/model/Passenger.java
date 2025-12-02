package com.example.flight.demo.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String currency;
    private String email;
    private boolean vip;

    @ElementCollection
    private List<String> tickets = new ArrayList<>();

    public Passenger() {}

    public Passenger(String name, String currency) {
        this.name = name;
        this.currency = currency;
    }

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public List<String> getTickets() { return tickets; }
    public void setTickets(List<String> tickets) { this.tickets = tickets; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public boolean isVip() { return vip; }
    public void setVip(boolean vip) { this.vip = vip; }
}
