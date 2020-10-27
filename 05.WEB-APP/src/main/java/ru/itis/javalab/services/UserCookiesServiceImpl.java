package ru.itis.javalab.services;

import ru.itis.javalab.models.UserCookie;
import ru.itis.javalab.repositories.CookiesRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;

/**
 * created: 21-10-2020 - 20:58
 * project: 05.WEB-APP
 *
 * @author dinar
 * @version v0.1
 */
public class UserCookiesServiceImpl implements UserCookiesService {
    private final CookiesRepository cookiesRepository;

    public UserCookiesServiceImpl(CookiesRepository cookiesRepository) {
        this.cookiesRepository = cookiesRepository;
    }

    @Override
    public Optional<UserCookie> getUserCookieByAuth(String auth) {
        return cookiesRepository.findByAuth(auth);
    }

    @Override
    public void addCookie(Long userId, HttpServletResponse response) {
        String auth;
        UserCookie userCookie;
        Optional<UserCookie> cook = cookiesRepository.findById(userId);
        if(cook.isPresent()) {
            auth = cook.get().getAuth();
        } else {
            do {
                auth = UUID.randomUUID().toString();
            } while (cookiesRepository.findByAuth(auth).isPresent());
            userCookie = new UserCookie(userId, auth);
            cookiesRepository.save(userCookie);
        }

        Cookie cookie = new Cookie("auth", auth);
        cookie.setMaxAge(60 * 60 * 24 * 365);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
