package ru.itis.springboot.exceptions;

/**
 * created: 18-04-2021 - 21:05
 * project: SpringBoot
 *
 * @author dinar
 * @version v0.1
 */
public class InvalidRefreshTokenException extends RuntimeException {

    private String message;

    public InvalidRefreshTokenException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return String.format("Token %s is invalid", message);
    }
}
