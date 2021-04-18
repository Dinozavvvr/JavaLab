package ru.itis.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.springboot.models.User;

import java.util.Optional;

/**
 * created: 18-04-2021 - 12:39
 * project: SpringBoot
 *
 * @author dinar
 * @version v0.1
 */
public interface UsersRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
