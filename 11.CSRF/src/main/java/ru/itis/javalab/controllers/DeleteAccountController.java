package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * DeleteAccountController
 * created: 23-01-2021 - 22:28
 * project: 11.CSRF
 *
 * @author dinar
 * @version v0.1
 */
@Controller
public class DeleteAccountController {

    private final UserService userService;

    @Autowired
    public DeleteAccountController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/deleteAccount", method = RequestMethod.POST)
    public String deleteAccount(HttpServletRequest request) {
        Long id = (Long) request.getSession(false).getAttribute("user_id");
        Optional<User> user = userService.getById(id);

        user.ifPresent(usr -> {
            usr.setIsDeleted(true);
            userService.update(usr);
        });

        return "redirect:signIn";
    }
}
