//package com.example.flight.demo.config;
//
//import com.example.flight.demo.model.*;
//import com.example.flight.demo.model.AirlineEmployee.Role;
//import com.example.flight.demo.repository.*;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class DatabaseInitializer implements CommandLineRunner {
//
//    private final AirplaneRepository airplaneRepository;
//    private final StaffRepository staffRepository;
//    private final PassengerRepository passengerRepository;
//    private final NoticeBoardRepository noticeBoardRepository;
//    private final FlightRepository flightRepository;
//    private final TicketRepository ticketRepository;
//    private final LuggageRepository luggageRepository;
//    private final FlightAssignmentRepository flightAssignmentRepository;
//
//    public DatabaseInitializer(AirplaneRepository airplaneRepository,
//                               StaffRepository staffRepository,
//                               PassengerRepository passengerRepository,
//                               NoticeBoardRepository noticeBoardRepository,
//                               FlightRepository flightRepository,
//                               TicketRepository ticketRepository,
//                               LuggageRepository luggageRepository,
//                               FlightAssignmentRepository flightAssignmentRepository) {
//
//        this.airplaneRepository = airplaneRepository;
//        this.staffRepository = staffRepository;
//        this.passengerRepository = passengerRepository;
//        this.noticeBoardRepository = noticeBoardRepository;
//        this.flightRepository = flightRepository;
//        this.ticketRepository = ticketRepository;
//        this.luggageRepository = luggageRepository;
//        this.flightAssignmentRepository = flightAssignmentRepository;
//    }
//
//    @Override
//    @Transactional
//    public void run(String... args) {
//
//        // dacă avem deja date, nu mai populăm
//        if (airplaneRepository.count() > 0) {
//            return;
//        }
//
//        // ---------- 1. Airplanes ----------
//        List<Airplane> airplanes = new ArrayList<>();
//        for (int n = 100; n <= 105; n++) {
//            Airplane airplane = new Airplane();
//            airplane.setNumber(n);
//            airplanes.add(airplane);
//        }
//        airplaneRepository.saveAll(airplanes);
//
//        // ---------- 2. Staff (moștenire: AirlineEmployee + AirportEmployee) ----------
//        List<Staff> staffList = new ArrayList<>();
//
//        AirlineEmployee captain = new AirlineEmployee(
//                "John Captain",
//                Role.PILOT,
//                "LIC-1000"
//        );
//        captain.setWorkStart(LocalDate.of(2020, 1, 10));
//        staffList.add(captain);
//
//        AirlineEmployee copilot = new AirlineEmployee(
//                "Anna Copilot",
//                Role.COPILOT,
//                "LIC-1001"
//        );
//        copilot.setWorkStart(LocalDate.of(2021, 3, 5));
//        staffList.add(copilot);
//
//        AirlineEmployee attendant = new AirlineEmployee(
//                "Maria Attendant",
//                Role.FLIGHT_ATTENDANT,
//                "LIC-1002"
//        );
//        attendant.setWorkStart(LocalDate.of(2022, 6, 15));
//        staffList.add(attendant);
//
//        AirportEmployee engineer = new AirportEmployee(
//                "Alex Engineer",
//                "Aircraft Engineer",
//                "Maintenance"
//        );
//        staffList.add(engineer);
//
//        AirportEmployee ground = new AirportEmployee(
//                "George Ground",
//                "Ramp Agent",
//                "Ground Operations"
//        );
//        staffList.add(ground);
//
//        staffRepository.saveAll(staffList);
//
//        // ---------- 3. Notice boards ----------
//        NoticeBoard todayBoard = new NoticeBoard();
//        todayBoard.setDate(LocalDate.now());
//
//        NoticeBoard tomorrowBoard = new NoticeBoard();
//        tomorrowBoard.setDate(LocalDate.now().plusDays(1));
//
//        noticeBoardRepository.save(todayBoard);
//        noticeBoardRepository.save(tomorrowBoard);
//
//        // ---------- 4. Flights ----------
//        List<Flight> flights = new ArrayList<>();
//
//        Flight f1 = new Flight();
//        f1.setName("F100");
//        f1.setAirplane(airplanes.get(0));
//        f1.setDepartureTime(LocalDateTime.now().plusHours(2));
//        f1.setArrivalTime(LocalDateTime.now().plusHours(5));
//        f1.setNoticeBoard(todayBoard);
//        flights.add(f1);
//
//        Flight f2 = new Flight();
//        f2.setName("F200");
//        f2.setAirplane(airplanes.get(1));
//        f2.setDepartureTime(LocalDateTime.now().plusHours(3));
//        f2.setArrivalTime(LocalDateTime.now().plusHours(6));
//        f2.setNoticeBoard(todayBoard);
//        flights.add(f2);
//
//        Flight f3 = new Flight();
//        f3.setName("F300");
//        f3.setAirplane(airplanes.get(2));
//        f3.setDepartureTime(LocalDateTime.now().plusDays(1).withHour(9).withMinute(0));
//        f3.setArrivalTime(LocalDateTime.now().plusDays(1).withHour(12).withMinute(0));
//        f3.setNoticeBoard(tomorrowBoard);
//        flights.add(f3);
//
//        flightRepository.saveAll(flights);
//
//        // ---------- 5. Passengers ----------
//        Passenger p1 = new Passenger();
//        p1.setName("Alice Passenger");
//        p1.setEmail("alice@example.com");
//        p1.setCurrency("EUR");
//        p1.setVip(false);
//
//        Passenger p2 = new Passenger();
//        p2.setName("Bob VIP");
//        p2.setEmail("bob@example.com");
//        p2.setCurrency("EUR");
//        p2.setVip(true);
//
//        Passenger p3 = new Passenger();
//        p3.setName("Charlie Normal");
//        p3.setEmail("charlie@example.com");
//        p3.setCurrency("USD");
//        p3.setVip(false);
//
//        passengerRepository.saveAll(List.of(p1, p2, p3));
//
//        // ---------- 6. Tickets ----------
//        Ticket t1 = new Ticket();
//        t1.setFlight(f1);
//        t1.setPassenger(p1);
//        t1.setSeatNumber("1A");
//        t1.setPrice(199.99);
//
//        Ticket t2 = new Ticket();
//        t2.setFlight(f1);
//        t2.setPassenger(p2);
//        t2.setSeatNumber("1B");
//        t2.setPrice(299.99);
//
//        Ticket t3 = new Ticket();
//        t3.setFlight(f2);
//        t3.setPassenger(p3);
//        t3.setSeatNumber("12C");
//        t3.setPrice(149.50);
//
//        ticketRepository.saveAll(List.of(t1, t2, t3));
//
//        // ---------- 7. Luggage ----------
//        Luggage l1 = new Luggage();
//        l1.setTicket(t1);
//
//        Luggage l2 = new Luggage();
//        l2.setTicket(t2);
//
//        Luggage l3 = new Luggage();
//        l3.setTicket(t3);
//
//        luggageRepository.saveAll(List.of(l1, l2, l3));
//
//        // ---------- 8. Flight assignments ----------
//        FlightAssignment a1 = new FlightAssignment();
//        a1.setFlight(f1);
//        a1.setStaff(captain);
//
//        FlightAssignment a2 = new FlightAssignment();
//        a2.setFlight(f1);
//        a2.setStaff(copilot);
//
//        FlightAssignment a3 = new FlightAssignment();
//        a3.setFlight(f1);
//        a3.setStaff(attendant);
//
//        FlightAssignment a4 = new FlightAssignment();
//        a4.setFlight(f2);
//        a4.setStaff(engineer);
//
//        flightAssignmentRepository.saveAll(List.of(a1, a2, a3, a4));
//    }
//}