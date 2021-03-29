package ru.itis.javalab.services;

import ru.itis.javalab.dto.UserSignInForm;
import ru.itis.javalab.models.User;

import javax.servlet.http.HttpServletResponse;

/**
 * created: 21-02-2021 - 14:11
 * project: SemesterWork
 *
 * @author dinar
 * @version v0.1
 */
public interface SignInService {

    User login(UserSignInForm userSignInForm);

    void rememberUser(User user, HttpServletResponse response);

}
