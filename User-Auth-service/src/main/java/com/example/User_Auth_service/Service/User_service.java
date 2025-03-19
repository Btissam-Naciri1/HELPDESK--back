package com.example.User_Auth_service.Service;

import com.example.User_Auth_service.Model.App_user;
import com.example.User_Auth_service.Model.AdminN2;
import com.example.User_Auth_service.Model.Groupe;
import com.example.User_Auth_service.Repository.User_Repository;
import com.example.User_Auth_service.Util.JwtUtil;
import com.example.User_Auth_service.Util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class User_service {

    @Autowired
    private User_Repository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public String authenticateUser(String email, String password) {
        Optional<App_user> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("Invalid credentials");
        }

        App_user user = userOptional.get();

        // ✅ Use PasswordUtil to verify hashed password
        if (!PasswordUtil.verifyPassword(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // ✅ Extract user role
        String role = user.getTypeUtilisateur().name();

        // ✅ If user is AdminN2, get assigned groups
        List<String> groupNames = List.of(); // Default empty list
        if (user instanceof AdminN2 adminN2) {
            groupNames = adminN2.getGroups().stream()
                    .map(Groupe::getNom)
                    .collect(Collectors.toList());
        }

        // ✅ Generate token with email, role, and groups
        return jwtUtil.generateToken(email, role, groupNames);
    }

    // ✅ User Registration Method (Fixing the issue)
    public App_user registerUser(String email, String nomcomplet, String password, String typeUtilisateur) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("User already exists!");
        }

        // ✅ Hash the password before saving
        String encodedPassword = PasswordUtil.hashPassword(password);

        // ✅ Create user with hashed password
        App_user newUser = new App_user(email, nomcomplet, encodedPassword, com.example.User_Auth_service.enums.Type_utilisateur.valueOf(typeUtilisateur));
        return userRepository.save(newUser);
    }

    // ✅ Fetch users dynamically based on search input
    public List<App_user> findUsers(String search) {
        if (search == null || search.isBlank()) {
            return userRepository.findAll(); // Return all users if no search term
        }
        return userRepository.findByNomcompletContainingIgnoreCase(search);
    }

    // ✅ Fetch user by ID
    public Optional<App_user> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
