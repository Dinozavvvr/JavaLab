package ru.itis.javalab.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.security.PermitAll;

/**
 * created: 20-02-2021 - 23:04
 * project: SemesterWork
 *
 * @author dinar
 * @version v0.1
 */

@Controller
public class LoginController {

    @PermitAll
    @GetMapping("/signin")
    public String getLoginPage(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/account";
        }
        return "login";
    }

}
