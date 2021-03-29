package ru.itis.javalab.services;

import ru.itis.javalab.dto.UserSignUpForm;
import ru.itis.javalab.models.User;

/**
 * created: 20-02-2021 - 23:27
 * project: SemesterWork
 *
 * @author dinar
 * @version v0.1
 */
public interface SignUpService {

    User signUp(UserSignUpForm userSignUpForm);

    User.State confirmUser(String code);

}
