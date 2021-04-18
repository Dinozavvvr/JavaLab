package ru.itis.springboot.exceptions;

import javax.naming.AuthenticationException;

/**
 * created: 18-04-2021 - 12:46
 * project: SpringBoot
 *
 * @author dinar
 * @version v0.1
 */

public class UserAlreadyExistException extends AuthenticationException {

    @Override
    public String getMessage() {
        return "user already exist";
    }
}
