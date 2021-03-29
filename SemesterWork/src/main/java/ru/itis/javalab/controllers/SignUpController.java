package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.javalab.dto.UserSignUpForm;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.SignUpService;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;

/**
 * created: 20-02-2021 - 23:04
 * project: SemesterWork
 *
 * @author dinar
 * @version v0.1
 */
@Controller
public class SignUpController {

    private final SignUpService signUpService;

    @Autowired
    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @PermitAll
    @GetMapping("/signup")
    public String getSignUpPage(Model model) {
        model.addAttribute("userSignUpForm", new UserSignUpForm());
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@Valid UserSignUpForm userSignUpForm,
                         BindingResult bindingResult,
                         Model model) {

        if (!bindingResult.hasErrors()) {
            User user = signUpService.signUp(userSignUpForm);
            if (user != null) {
                return "redirect:/signin";
            }
            return "redirect:/signup";
        } else {
            model.addAttribute("userSignUpForm", userSignUpForm);
            return "signup";
        }
    }

}
