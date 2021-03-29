package ru.itis.javalab.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.javalab.models.User;
import ru.itis.javalab.security.details.UserDetailsImpl;
import ru.itis.javalab.services.CookieService;
import ru.itis.javalab.services.PostService;
import ru.itis.javalab.services.UserService;
import ru.itis.javalab.utils.Sessions;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * created: 20-02-2021 - 23:12
 * project: SemesterWork
 *
 * @author dinar
 * @version v0.1
 */

@Controller
public class AccountController {

    private final UserService userService;

    private final PostService postService;

    public AccountController(UserService userService,
                             PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/account")
    public ModelAndView getAccountPage(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return new ModelAndView("account", writeTemplate(userDetails.getUser()));
    }

    private Map<String, Object> writeTemplate(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("email", user.getEmail());
        map.put("login", user.getLogin());
        map.put("posts", postService.getPostsByUser(user));

        return map;
    }
}
