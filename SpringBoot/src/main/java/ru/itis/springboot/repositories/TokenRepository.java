package ru.itis.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.springboot.models.RefreshToken;

import java.util.Optional;

/**
 * created: 18-04-2021 - 20:56
 * project: SpringBoot
 *
 * @author dinar
 * @version v0.1
 */
public interface TokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByFingerprint(String fingerprint);

}
