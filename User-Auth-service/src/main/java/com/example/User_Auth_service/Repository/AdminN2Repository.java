package com.example.User_Auth_service.Repository;

import com.example.User_Auth_service.Model.AdminN2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminN2Repository extends JpaRepository<AdminN2, Long> {
    Optional<AdminN2> findByEmail(String email);
}
