package ru.itis.javalab.services;

import org.springframework.stereotype.Service;
import ru.itis.javalab.models.UserCookie;
import ru.itis.javalab.repositories.CookieRepository;

import java.util.Optional;

/**
 * created: 21-02-2021 - 16:07
 * project: SemesterWork
 *
 * @author dinar
 * @version v0.1
 */

@Service
public class CookieServiceImpl implements CookieService {

    private final CookieRepository cookieRepository;

    public CookieServiceImpl(CookieRepository cookieRepository) {
        this.cookieRepository = cookieRepository;
    }

    @Override
    public UserCookie getBySessionId(String sessionId) {
        Optional<UserCookie> userCookie = cookieRepository.getBySessionId(sessionId);
        return userCookie.orElse(null);
    }
}
