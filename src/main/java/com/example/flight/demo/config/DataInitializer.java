package com.example.flight.demo.config;

import com.example.flight.demo.model.*;
import com.example.flight.demo.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PassengerRepository passengerRepository;
    private final AirplaneRepository airplaneRepository;
    private final AirlineEmployeeRepository airlineEmployeeRepository;
    private final AirportEmployeeRepository airportEmployeeRepository;
    private final NoticeBoardRepository noticeBoardRepository;
    private final FlightRepository flightRepository;
    private final TicketRepository ticketRepository;
    private final LuggageRepository luggageRepository;
    private final FlightAssignmentRepository flightAssignmentRepository;

    public DataInitializer(PassengerRepository passengerRepository,
                           AirplaneRepository airplaneRepository,
                           AirlineEmployeeRepository airlineEmployeeRepository,
                           AirportEmployeeRepository airportEmployeeRepository,
                           NoticeBoardRepository noticeBoardRepository,
                           FlightRepository flightRepository,
                           TicketRepository ticketRepository,
                           LuggageRepository luggageRepository,
                           FlightAssignmentRepository flightAssignmentRepository) {
        this.passengerRepository = passengerRepository;
        this.airplaneRepository = airplaneRepository;
        this.airlineEmployeeRepository = airlineEmployeeRepository;
        this.airportEmployeeRepository = airportEmployeeRepository;
        this.noticeBoardRepository = noticeBoardRepository;
        this.flightRepository = flightRepository;
        this.ticketRepository = ticketRepository;
        this.luggageRepository = luggageRepository;
        this.flightAssignmentRepository = flightAssignmentRepository;
    }

    @Override
    public void run(String... args) {
        seedPassengers();
        seedAirplanes();
        seedAirlineEmployees();
        seedAirportEmployees();
        seedNoticeBoards();
        seedFlights();
        seedTickets();
        seedLuggage();
        seedAssignments();
    }

    private void seedPassengers() {
        if (passengerRepository.count() > 0) return;
        List<Passenger> passengers = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Passenger p = new Passenger();
            p.setName("Passenger " + i);
            p.setCurrency("EUR");
            p.setEmail("passenger" + i + "@example.com");
            p.setVip(i % 3 == 0);
            passengers.add(p);
        }
        passengerRepository.saveAll(passengers);
    }

    private void seedAirplanes() {
        if (airplaneRepository.count() > 0) return;
        List<Airplane> airplanes = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Airplane airplane = new Airplane();
            airplane.setNumber(100 + i);
            airplanes.add(airplane);
        }
        airplaneRepository.saveAll(airplanes);
    }

    private void seedAirlineEmployees() {
        if (airlineEmployeeRepository.count() > 0) return;
        AirlineRole[] roles = AirlineRole.values();
        List<AirlineEmployee> employees = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            AirlineEmployee e = new AirlineEmployee();
            e.setName("Crew Member " + i);
            e.setRole(roles[i % roles.length]);
            e.setLicenseNumber("LIC" + (1000 + i));
            e.setWorkStart(LocalDate.now().minusDays(30L + i));
            employees.add(e);
        }
        airlineEmployeeRepository.saveAll(employees);
    }

    private void seedAirportEmployees() {
        if (airportEmployeeRepository.count() > 0) return;
        List<AirportEmployee> employees = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            AirportEmployee e = new AirportEmployee();
            e.setName("Ground Staff " + i);
            e.setDesignation("Designation " + i);
            e.setDepartment("Dept " + (i % 4 + 1));
            employees.add(e);
        }
        airportEmployeeRepository.saveAll(employees);
    }

    private void seedNoticeBoards() {
        if (noticeBoardRepository.count() > 0) return;
        List<NoticeBoard> boards = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            NoticeBoard board = new NoticeBoard();
            board.setDate(LocalDate.now().plusDays(i));
            boards.add(board);
        }
        noticeBoardRepository.saveAll(boards);
    }

    private void seedFlights() {
        if (flightRepository.count() > 0) return;
        List<Airplane> airplanes = airplaneRepository.findAll();
        List<NoticeBoard> boards = noticeBoardRepository.findAll();
        List<Flight> flights = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Flight flight = new Flight();
            flight.setName("FL-" + (100 + i));
            flight.setStatus(FlightStatus.values()[i % FlightStatus.values().length]);
            flight.setDepartureTime(LocalDateTime.now().plusDays(i));
            flight.setArrivalTime(LocalDateTime.now().plusDays(i).plusHours(2));
            flight.setAirplane(airplanes.get((i - 1) % airplanes.size()));
            flight.setNoticeBoard(boards.get((i - 1) % boards.size()));
            flights.add(flight);
        }
        flightRepository.saveAll(flights);
    }

    private void seedTickets() {
        if (ticketRepository.count() > 0) return;
        List<Passenger> passengers = passengerRepository.findAll();
        List<Flight> flights = flightRepository.findAll();
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Ticket ticket = new Ticket();
            ticket.setPassenger(passengers.get((i - 1) % passengers.size()));
            ticket.setFlight(flights.get((i - 1) % flights.size()));
            ticket.setPrice(BigDecimal.valueOf(150 + i * 10));
            ticket.setSeatNumber("A" + i);
            tickets.add(ticket);
        }
        ticketRepository.saveAll(tickets);
    }

    private void seedLuggage() {
        if (luggageRepository.count() > 0) return;
        List<Ticket> tickets = ticketRepository.findAll();
        List<Luggage> luggageList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Luggage luggage = new Luggage();
            luggage.setTicket(tickets.get((i - 1) % tickets.size()));
            luggage.setStatus(LuggageStatus.values()[i % LuggageStatus.values().length]);
            luggageList.add(luggage);
        }
        luggageRepository.saveAll(luggageList);
    }

    private void seedAssignments() {
        if (flightAssignmentRepository.count() > 0) return;
        List<Flight> flights = flightRepository.findAll();
        List<AirlineEmployee> employees = airlineEmployeeRepository.findAll();
        List<FlightAssignment> assignments = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            FlightAssignment assignment = new FlightAssignment();
            assignment.setFlight(flights.get((i - 1) % flights.size()));
            assignment.setStaff(employees.get((i - 1) % employees.size()));
            assignments.add(assignment);
        }
        flightAssignmentRepository.saveAll(assignments);
    }
}
