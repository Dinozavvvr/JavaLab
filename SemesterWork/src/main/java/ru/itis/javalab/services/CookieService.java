package ru.itis.javalab.services;

import ru.itis.javalab.models.UserCookie;

/**
 * created: 21-02-2021 - 16:05
 * project: SemesterWork
 *
 * @author dinar
 * @version v0.1
 */

public interface CookieService {

    UserCookie getBySessionId(String sessionId);

}
