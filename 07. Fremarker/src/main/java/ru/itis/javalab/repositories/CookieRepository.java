package ru.itis.javalab.repositories;

import ru.itis.javalab.models.Cookie;

import java.util.Optional;

/**
 * created: 19-11-2020 - 0:50
 * project: 07. Fremarker
 *
 * @author dinar
 * @version v0.1
 */
public interface CookieRepository extends CrudRepository<Cookie, Long>{

    Optional<Cookie> findBySessionId(String sessionId);

    Optional<Cookie> findByUserId(Long id);

}
