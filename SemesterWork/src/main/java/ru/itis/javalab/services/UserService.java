package ru.itis.javalab.services;

import ru.itis.javalab.models.User;

/**
 * created: 21-02-2021 - 16:14
 * project: SemesterWork
 *
 * @author dinar
 * @version v0.1
 */
public interface UserService {

    User getById(Long id);

    User getByLogin(String username);
}
