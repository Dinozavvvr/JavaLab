package ru.itis.springboot.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created: 18-04-2021 - 22:26
 * project: SpringBoot
 *
 * @author dinar
 * @version v0.1
 */
@RestController
public class ProfileController {

    @GetMapping("/api/0.1/profile")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> getProfilePage() {
        return ResponseEntity.ok("good job");
    }

}
