package ru.itis.javalab.services;

import ru.itis.javalab.models.UserCookie;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * created: 21-10-2020 - 20:57
 * project: 05.WEB-APP
 *
 * @author dinar
 * @version v0.1
 */
public interface UserCookiesService {
    Optional<UserCookie> getUserCookieByAuth(String auth);
    void addCookie(Long userId, HttpServletResponse response);
}
