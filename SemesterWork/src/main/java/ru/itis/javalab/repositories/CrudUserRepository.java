package ru.itis.javalab.repositories;

import ru.itis.javalab.models.User;

import java.util.Optional;

/**
 * created: 28-02-2021 - 16:20
 * project: SemesterWork
 *
 * @author dinar
 * @version v0.1
 */
public interface CrudUserRepository extends CrudRepository<User, Long> {

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    Optional<User> findByLogin(String login);

    Optional<User> findByConfirmCode(String code);

}
