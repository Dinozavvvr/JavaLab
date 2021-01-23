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
 * SignUpController
 * created: 23-01-2021 - 21:20
 * project: 11.CSRF
 *
 * @author dinar
 * @version v0.1
 */

@Controller
public class SignUpController {

    private final UserService userService;

    @Autowired
    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/signUp")
    public ModelAndView getSignUpPage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("signup");
        modelAndView.addObject(request.getAttribute("_csrf_token"));
        return modelAndView;
    }

    @RequestMapping(path = "/signUp", method = RequestMethod.POST)
    public String signup(@RequestParam String username,
                               @RequestParam String password,
                               HttpSession session) {

        if (userService.getByUsername(username).isEmpty()) {
            User user = User.builder()
                    .username(username)
                    .password(password)
                    .build();

            userService.signUp(user);
            Optional<User> userOptional = userService.getByUsername(username);

            userOptional.ifPresent(usr -> session.setAttribute("user_id", usr.getId()));
            return "redirect:profile";
        } else {
            return "redirect:signUp";
        }

    }

}
