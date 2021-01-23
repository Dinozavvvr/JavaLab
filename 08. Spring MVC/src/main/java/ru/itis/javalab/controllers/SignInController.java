package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.UsersService;

import javax.servlet.http.HttpSession;

/**
 * SignInController
 * created: 29-11-2020 - 20:46
 * project: 08. Spring MVC
 *
 * @author dinar
 * @version v0.1
 */
@Controller
public class SignInController {

    private final UsersService usersService;

    @Autowired
    public SignInController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(path = "/signin")
    public String getSignInPaga(Model model) {
        return "signin";
    }

    @RequestMapping(path = "/signin", method = RequestMethod.POST)
    public String login(HttpSession httpSession,
                        @RequestParam String login,
                        @RequestParam String password,
                        @Nullable @RequestParam String remember) {
        Long id = usersService.login(login, password);

        if (id != null) {
            User user = usersService.getById(id).get();

            httpSession.setAttribute("user", id);
            return "redirect:account";
        } else {
            return "signin";
        }
    }

}
