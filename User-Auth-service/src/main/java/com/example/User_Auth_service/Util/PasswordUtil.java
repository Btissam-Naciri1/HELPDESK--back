package com.example.User_Auth_service.Util;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtil {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // ✅ Generate a Secure Random Password (16 characters)
    public static String generatePassword() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[6];
        random.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    // ✅ Hash a Password with BCrypt
    public static String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    // ✅ Verify a password (for authentication)
    public static boolean verifyPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }

    public static void main(String[] args) {
        // 🔹 Generate a random password
        String randomPassword = generatePassword();
        System.out.println("Generated Password: " + randomPassword);

        // 🔹 Hash the generated password
        String hashedPassword = hashPassword(randomPassword);
        System.out.println("Hashed Password: " + hashedPassword);

        // 🔹 Verify the password
        System.out.println("Password Match: " + verifyPassword(randomPassword, hashedPassword));
    }
}
