package com.example.User_Auth_service.Controller;

import com.example.User_Auth_service.Auth.JwtRequestFilter;
import com.example.User_Auth_service.DTO.UserDto;
import com.example.User_Auth_service.Model.App_user;
import com.example.User_Auth_service.Service.User_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/auth")
public class User_controller {

    @Autowired
    private User_service authService;
    @Autowired
    private JwtRequestFilter JwtUtil;

    // ✅ Login Endpoint (Handles Authentication)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        try {
            String email = credentials.get("email");
            String password = credentials.get("password");

            // ✅ Authenticate the user and return a JWT
            String token = authService.authenticateUser(email, password);

            return ResponseEntity.ok(Map.of("token", token));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // ✅ Registration Endpoint (Handles User Creation)
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, Object> userData) {
        try {
            String email = (String) userData.get("email");
            String nomcomplet = (String) userData.get("nomcomplet");
            String password = (String) userData.get("password");
            String role = (String) userData.get("typeUtilisateur");



            // ✅ Register user
            App_user newUser = authService.registerUser(email,nomcomplet, password, role);

            return ResponseEntity.ok(Map.of(
                    "message", "User registered successfully",
                    "userId", newUser.getId()
            ));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // ✅ Fetch User by ID (Needed for Feign Client in `tickets-service`)
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<App_user> userOptional = authService.getUserById(id);

        if (userOptional.isPresent()) {
            App_user user = userOptional.get();
            UserDto userDTO = new UserDto(
                    user.getEmail(),
                    user.getNomcomplet(),
                    user.getTypeUtilisateur()

            );
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers(@RequestParam(required = false) String search) {
        List<App_user> users = authService.findUsers(search);

        // Convert users to DTO (only necessary fields)
        List<UserDto> userDtos = users.stream()
                .map(user -> new UserDto( user.getEmail(), user.getNomcomplet(), user.getTypeUtilisateur()))
                .toList();

        return ResponseEntity.ok(userDtos);
    }

}
