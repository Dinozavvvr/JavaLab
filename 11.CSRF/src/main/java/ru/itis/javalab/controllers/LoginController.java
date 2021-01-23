package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * LoginController
 * created: 23-01-2021 - 21:59
 * project: 11.CSRF
 *
 * @author dinar
 * @version v0.1
 */
@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/signIn")
    public ModelAndView getLoginPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject(request.getAttribute("_csrf_token"));
        return modelAndView;
    }

    @RequestMapping(path = "/signIn", method = RequestMethod.POST)
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session) {
        Optional<User> user;

        if (userService.login(username, password)) {
           user = userService.getByUsername(username);
           user.ifPresent(usr -> session.setAttribute("user_id", usr.getId()));
           return "redirect:profile";
        } else {
            return "redirect:signIn";
        }
        
    }
}
