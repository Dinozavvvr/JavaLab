package ru.itis.javalab.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.UserSignInForm;
import ru.itis.javalab.models.User;
import ru.itis.javalab.models.UserCookie;
import ru.itis.javalab.repositories.CookieRepository;
import ru.itis.javalab.repositories.UserRepository;
import ru.itis.javalab.utils.Cookies;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;

/**
 * created: 21-02-2021 - 14:12
 * project: SemesterWork
 *
 * @author dinar
 * @version v0.1
 */
@Service
public class SignInServiceImpl implements SignInService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final CookieRepository cookieRepository;

    public SignInServiceImpl(UserRepository userRepository,
                             PasswordEncoder passwordEncoder,
                             CookieRepository cookieRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.cookieRepository = cookieRepository;
    }

    @Override
    public User login(UserSignInForm userSignInForm) {
        Optional<User> optionalUser = userRepository
                .findByLogin(userSignInForm.getLogin());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (passwordEncoder.matches(userSignInForm.getPassword(), user.getPassword())){
                return user;
            }
        }
        return null;
    }

    @Override
    public void rememberUser(User user, HttpServletResponse response) {
        String sessionId;
        do {
            sessionId = UUID.randomUUID().toString();
        } while (cookieRepository.getBySessionId(sessionId).isPresent());

        UserCookie userCookie = UserCookie.builder()
                .user(user)
                .sessionId(sessionId)
                .build();

        cookieRepository.save(userCookie);

        Cookie cookie = new Cookie(Cookies.SESSION_ID.value, sessionId);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 365);
    }
}
