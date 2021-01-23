package ru.itis.javalab.services;

import ru.itis.javalab.models.User;

import java.util.Optional;

/**
 * UserService
 * created: 23-01-2021 - 21:15
 * project: 11.CSRF
 *
 * @author dinar
 * @version v0.1
 */
public interface UserService {

    void signUp(User user);

    void update(User user);

    boolean login(String username, String password);

    Optional<User> getByUsername(String username);

    Optional<User> getById(Long id);
}
