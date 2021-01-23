package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.UsersService;

/**
 * UserController
 * created: 29-11-2020 - 16:12
 * project: 08. Spring MVC
 *
 * @author dinar
 * @version v0.1
 */
@Controller
public class SignUpController {

    private final UsersService usersService;

    @Autowired
    public SignUpController(UsersService usersService) {
        this.usersService = usersService;
    }

    /* deprecated
    @RequestMapping(path = "/signup", method = RequestMethod.GET)
    public ModelAndView getSignUpPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signup");
        return modelAndView;
    }
    */


    @RequestMapping(path = "/signup")
    public String getSignUpPage() {
        return "signup";
    }

    @RequestMapping(path = "/signup", method = RequestMethod.POST)
    public String registerUser(User user) {
        Long id = usersService.signUp(user);
        if (id != null) {
            return "redirect:signin";
        }
        else {
            return "signup";
        }
    }
}
