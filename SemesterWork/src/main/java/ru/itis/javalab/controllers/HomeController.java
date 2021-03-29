package ru.itis.javalab.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.security.PermitAll;

/**
 * created: 21-03-2021 - 23:53
 * project: SemesterWork
 *
 * @author dinar
 * @version v0.1
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @PermitAll
    @GetMapping
    public String getHomePage(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/account";
        } else {
            return "redirect:/signin";
        }
    }

}
