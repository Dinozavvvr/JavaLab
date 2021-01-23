package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.CookieRepository;
import ru.itis.javalab.repositories.UserRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;

/**
 * created: 19-11-2020 - 0:17
 * project: 07. Fremarker
 *
 * @author dinar
 * @version v0.1
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CookieRepository cookieRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final String SESSION_ID = "session_id";

    @Override
    public Long signUp(User user) {
        if (userRepository.findByEmail(user.getEmail()).isEmpty() && userRepository.findByLogin(user.getLogin()).isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public Long login(String login, String password) {
        Optional<User> user = userRepository.findByLogin(login);
        System.out.println(user);
        if(user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user.get().getId();
        }
        return null;
    }

    @Override
    public void rememberUser(HttpServletResponse response, Long userId) {

        Optional<ru.itis.javalab.models.Cookie> userCookie = cookieRepository.findByUserId(userId);

        Cookie cookie;

        if (userCookie.isPresent()) {
            cookie = new Cookie(SESSION_ID, userCookie.get().getSessionId());
        } else {
            String sessionId;

            do {
                sessionId = UUID.randomUUID().toString();
            } while (cookieRepository.findBySessionId(sessionId).isPresent());

            ru.itis.javalab.models.Cookie newUserCookie = new ru.itis.javalab.models.Cookie(userId, sessionId);
            System.out.println(newUserCookie);
            cookieRepository.save(newUserCookie);

            cookie = new Cookie(SESSION_ID, sessionId);
        }

        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 90);

        response.addCookie(cookie);
    }

    @Override
    public Long getUserId(String sessionId) {
        Optional<ru.itis.javalab.models.Cookie> cookie = cookieRepository.findBySessionId(sessionId);
        return cookie.map(ru.itis.javalab.models.Cookie::getUserId).orElse(null);
    }

    @Override
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }
}
