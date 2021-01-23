package ru.itis.javalab.services;

import ru.itis.javalab.models.User;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * created: 19-11-2020 - 0:16
 * project: 07. Fremarker
 *
 * @author dinar
 * @version v0.1
 */
public interface UserService {

    Long login(String login, String password);

    Long signUp(User user);

    void rememberUser(HttpServletResponse response, Long userId);

    Long getUserId(String sessionId);

    Optional<User> getById(Long id);
}
