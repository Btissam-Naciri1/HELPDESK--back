package com.example.User_Auth_service.Controller;

import com.example.User_Auth_service.Model.AdminN2;
import com.example.User_Auth_service.Service.AdminN2_Service;
import com.example.User_Auth_service.Util.JwtUtil;
import com.example.User_Auth_service.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin-n2")
public class AdminN2_controller {

    @Autowired
    private AdminN2_Service adminN2Service;

    @Autowired
    private JwtUtil jwtUtil;


    // ✅ AdminN2 Registration (POST /admin-n2/register)
    @PostMapping("/register")
    public ResponseEntity<?> registerAdminN2(@RequestBody Map<String, Object> request) {
        try {
            String email = (String) request.get("email");
            String password = (String) request.get("password");
            Role role = Role.valueOf((String) request.get("role")); // ✅ Validate Role
            List<String> groupNames = (List<String>) request.get("groups"); // ✅ Group names

            AdminN2 newAdmin = adminN2Service.registerAdminN2(email, password, role, groupNames);
            return ResponseEntity.ok(Map.of("message", "AdminN2 registered successfully", "admin", newAdmin));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // ✅ AdminN2 Login (POST /admin-n2/login)
    @PostMapping("/login")
    public ResponseEntity<?> loginAdminN2(@RequestBody Map<String, String> request) {
        try {
            String email = request.get("email");
            String password = request.get("password");

            String token = adminN2Service.authenticateAdminN2(email, password);
            return ResponseEntity.ok(Map.of("token", token));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
