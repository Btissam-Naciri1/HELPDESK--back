package com.example.notif_service.controller;

import com.example.notif_service.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class Notifications_controller {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public String sendNotification(@RequestParam String to,
                                   @RequestParam String subject,
                                   @RequestParam String body) {
        emailService.sendEmail(to, subject, body);
        return "Email sent successfully to " + to;
    }
}
