package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.UserService;

import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * ProfileController
 * created: 23-01-2021 - 23:19
 * project: 11.CSRF
 *
 * @author dinar
 * @version v0.1
 */
@Controller
public class ProfileController {
    
    private final UserService userService;
    
    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }
    
    @RequestMapping(path = "/profile", method = RequestMethod.GET)
    public ModelAndView getProfilePage(HttpSession session) {
        Optional<User> user =
                userService.getById((Long) session.getAttribute("user_id"));

        if (user.isPresent() && !user.get().getIsDeleted()) {
            ModelAndView modelAndView = new ModelAndView("profile");
            modelAndView.addObject("user", user.get());
            return modelAndView;
        } else {
            return new ModelAndView("redirect:signIn");
        }
    }
}
