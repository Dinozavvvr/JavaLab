package ru.itis.springboot.service;

import ru.itis.springboot.dto.UserLoginDto;
import ru.itis.springboot.models.User;

/**
 * created: 18-04-2021 - 12:39
 * project: SpringBoot
 *
 * @author dinar
 * @version v0.1
 */
public interface LoginService {

    User login(UserLoginDto user);

}
