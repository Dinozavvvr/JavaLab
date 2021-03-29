package ru.itis.javalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.models.UserCookie;

import java.util.Optional;

/**
 * created: 21-02-2021 - 15:22
 * project: SemesterWork
 *
 * @author dinar
 * @version v0.1
 */
public interface CookieRepository extends JpaRepository<UserCookie, Long> {

    Optional<UserCookie> getBySessionId(String sessionId);

}
