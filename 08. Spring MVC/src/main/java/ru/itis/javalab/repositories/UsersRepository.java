package ru.itis.javalab.repositories;

import ru.itis.javalab.models.User;

import java.util.Optional;

/**
 * UsersRepository
 * created: 29-11-2020 - 15:39
 * project: 08. Spring MVC
 *
 * @author dinar
 * @version v0.1
 */
public interface UsersRepository extends CrudRepository<User, Long>{

    Optional<User> findByEmail(String email);

    Optional<User> findByLogin(String login);


}
