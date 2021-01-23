package ru.itis.javalab.services;

import ru.itis.javalab.models.User;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * UsersService
 * created: 29-11-2020 - 21:23
 * project: 08. Spring MVC
 *
 * @author dinar
 * @version v0.1
 */
public interface UsersService {

    Long login(String login, String password);

    Long signUp(User user);

    void rememberUser(HttpServletResponse response, Long userId);

    Long getUserId(String sessionId);

    Optional<User> getById(Long id);

}
