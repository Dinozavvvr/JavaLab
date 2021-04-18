package ru.itis.springboot.service;

import ru.itis.springboot.dto.UserSignupDto;
import ru.itis.springboot.exceptions.UserAlreadyExistException;
import ru.itis.springboot.models.User;

/**
 * created: 18-04-2021 - 12:38
 * project: SpringBoot
 *
 * @author dinar
 * @version v0.1
 */
public interface RegistrationService {

    User signup(UserSignupDto user) throws UserAlreadyExistException;

}
