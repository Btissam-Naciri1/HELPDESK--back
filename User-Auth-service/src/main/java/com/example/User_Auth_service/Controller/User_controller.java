package com.example.User_Auth_service.Controller;

import com.example.User_Auth_service.Model.App_user;
import com.example.User_Auth_service.Service.User_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class User_controller {

    @Autowired
    private User_service authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");
        String token = authService.authenticateUser(email, password);
        return ResponseEntity.ok(Map.of("token", token));
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> userData) {
        String email = userData.get("email");
        String password = userData.get("password");
        String typeUtilisateur = userData.get("typeUtilisateur");

        // ✅ Call the service to create the user
        App_user newUser = authService.registerUser(email, password, typeUtilisateur);

        return ResponseEntity.ok(Map.of(
                "message", "User registered successfully",
                "userId", newUser.getId()
        ));
    }
}