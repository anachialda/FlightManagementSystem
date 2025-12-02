package com.example.flight.demo.repository;

import com.example.flight.demo.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {
}