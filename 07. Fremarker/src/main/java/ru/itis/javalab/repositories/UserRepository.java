package ru.itis.javalab.repositories;

import ru.itis.javalab.models.User;

import java.util.Optional;

/**
 * created: 18-11-2020 - 22:24
 * project: 07. Fremarker
 *
 * @author dinar
 * @version v0.1
 */

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    Optional<User> findByLogin(String login);

}
