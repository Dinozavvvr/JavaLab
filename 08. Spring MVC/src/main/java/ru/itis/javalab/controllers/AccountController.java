package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.UsersService;

import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * AccountController
 * created: 30-11-2020 - 20:45
 * project: 08. Spring MVC
 *
 * @author dinar
 * @version v0.1
 */
@Controller
public class AccountController {

    private final UsersService usersService;

    @Autowired
    public AccountController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(path = "/account")
    public String getAccountPage(Model model,
                                 HttpSession httpSession) {
        Long id = (Long) httpSession.getAttribute("user");

        Optional<User> user = usersService.getById(id);
        user.ifPresent(value -> model.addAttribute("user", value));

        return "account";
    }

}
