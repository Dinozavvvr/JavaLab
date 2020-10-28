package ru.itis.javalab.services;

import ru.itis.javalab.models.User;

import java.util.List;
import java.util.Optional;

/**
 * created: 21-10-2020 - 19:44
 * project: 05.WEB-APP
 *
 * @author dinar
 * @version v0.1
 */
public interface UsersService {
    List<User> getAllByAge(Integer age);

    Optional<User> getByUsername(String username);

    void addUser(User user);

    String generateSecurePassword(String password);

    boolean verifyPassword(String password, String securePassword);

    Optional<User> getFirstByFirstnameAndLastname(String firstName, String lastName);

    List<User> getAll();

    Optional<User> getById(Long id);

    void update(User user);

    void removeById(Long id);

    void remove(User user);
}
