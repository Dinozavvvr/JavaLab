package ru.itis.javalab.repositories;

import ru.itis.javalab.models.User;

import java.util.Optional;

/**
 * UsersRepository
 * created: 23-01-2021 - 20:49
 * project: 11.CSRF
 *
 * @author dinar
 * @version v0.1
 */
public interface UsersRepository extends CrudRepository<Long, User> {

    Optional<User> findByUsername(String username);
    
}
