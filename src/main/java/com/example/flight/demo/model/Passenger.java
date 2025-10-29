package com.example.flight.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Passenger {
    private Long id;
    private String name;
    private String currency;
    private List<Long> tickets = new ArrayList<>();
    private String email;
    private boolean vip;

    public Passenger() {}
    public Passenger(Long id, String name, String currency) {
        this.id = id; this.name = name; this.currency = currency;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public List<Long> getTickets() { return tickets; }
    public void setTickets(List<Long> tickets) { this.tickets = tickets; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public boolean isVip() { return vip; }
    public void setVip(boolean vip) { this.vip = vip; }
}
