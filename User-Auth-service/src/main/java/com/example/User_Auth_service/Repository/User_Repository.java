package com.example.User_Auth_service.Repository;

import com.example.User_Auth_service.Model.App_user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface User_Repository extends JpaRepository<App_user, Long> {
    Optional<App_user> findByEmail(String email);
}
