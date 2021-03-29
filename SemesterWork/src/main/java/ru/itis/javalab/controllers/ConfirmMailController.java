package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.SignUpService;

import javax.annotation.security.PermitAll;

/**
 * created: 21-02-2021 - 13:23
 * project: SemesterWork
 *
 * @author dinar
 * @version v0.1
 */
@Controller
public class ConfirmMailController {

    private final SignUpService signUpService;

    @Autowired
    public ConfirmMailController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @PermitAll
    @GetMapping("/confirm")
    public String confirmMail(@RequestParam(value = "confirm_code") String code) {
        System.out.println(signUpService);
        if (signUpService.confirmUser(code) == User.State.CONFIRMED) {
            return "mail/mail_already_confirmed";
        }
        return "mail/ok_mail_confirm";

    }
}
