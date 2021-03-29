package ru.itis.javalab.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.javalab.security.details.UserDetailsImpl;

/**
 * created: 21-03-2021 - 23:31
 * project: Gradle_example
 *
 * @author dinar
 * @version v0.1
 */
@Controller
public class AdminController {

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public String getAdminPage(@AuthenticationPrincipal UserDetailsImpl userDetails,
                               Model model) {
        model.addAttribute("user", userDetails.getUser());
        return "admin";
    }
}
