package ru.itis.javalab.repositories;

import ru.itis.javalab.models.UserCookie;

import java.util.Optional;

/**
 * created: 21-10-2020 - 20:39
 * project: 05.WEB-APP
 *
 * @author dinar
 * @version v0.1
 */
public interface CookiesRepository extends CrudRepository<UserCookie> {
    Optional<UserCookie> findByAuth(String auth);
}
