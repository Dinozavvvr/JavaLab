package ru.itis.javalab.repositories;

import ru.itis.javalab.models.UserCookie;

import java.util.List;
import java.util.Optional;

/**
 * created: 28-02-2021 - 17:06
 * project: SemesterWork
 *
 * @author dinar
 * @version v0.1
 */
public interface CrudCookieRepository extends CrudRepository<UserCookie, Long>{

    Optional<UserCookie> getBySessionId(String sessionId);

}
