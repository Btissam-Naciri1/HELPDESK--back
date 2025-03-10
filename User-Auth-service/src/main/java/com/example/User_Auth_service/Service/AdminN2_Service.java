package com.example.User_Auth_service.Service;

import com.example.User_Auth_service.Model.AdminN2;
import com.example.User_Auth_service.Model.Groupe;
import com.example.User_Auth_service.Repository.AdminN2Repository;
import com.example.User_Auth_service.Repository.Groupe_Repository;
import com.example.User_Auth_service.Util.JwtUtil;
import com.example.User_Auth_service.Util.PasswordUtil;
import com.example.User_Auth_service.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminN2_Service {

    @Autowired
    private AdminN2Repository adminN2Repository;

    @Autowired
    private Groupe_Repository groupeRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // ✅ Register AdminN2
    public AdminN2 registerAdminN2(String email, String password, Role role, List<String> groupNames) {
        Optional<AdminN2> existingAdmin = adminN2Repository.findByEmail(email);
        if (existingAdmin.isPresent()) {
            throw new RuntimeException("AdminN2 with this email already exists!");
        }

        String hashedPassword = PasswordUtil.hashPassword(password);

        // Fetch groups from the database
        List<Groupe> groups = groupeRepository.findByNomIn(groupNames);

        // ✅ Ensure role is not NULL before saving
        if (role == null) {
            throw new RuntimeException("Role cannot be null for AdminN2!");
        }

        // ✅ Correct constructor usage
        AdminN2 newAdmin = new AdminN2(email, hashedPassword, role, groups);

        return adminN2Repository.save(newAdmin);
    }

    // ✅ Authenticate AdminN2 and generate JWT token
    public String authenticateAdminN2(String email, String password) {
        Optional<AdminN2> adminN2Optional = adminN2Repository.findByEmail(email);
        if (adminN2Optional.isEmpty()) {
            throw new RuntimeException("Invalid credentials");
        }

        AdminN2 adminN2 = adminN2Optional.get();

        // ✅ Validate password
        if (!PasswordUtil.verifyPassword(password, adminN2.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // ✅ Generate JWT token with role & groups
        String role = adminN2.getRole().name();
        List<String> groupNames = adminN2.getGroups().stream()
                .map(Groupe::getNom)
                .collect(Collectors.toList());

        return jwtUtil.generateToken(email, role, groupNames);
    }
}
