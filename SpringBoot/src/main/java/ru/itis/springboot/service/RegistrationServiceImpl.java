package ru.itis.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.springboot.dto.UserSignupDto;
import ru.itis.springboot.exceptions.UserAlreadyExistException;
import ru.itis.springboot.models.User;
import ru.itis.springboot.repositories.UsersRepository;

/**
 * created: 18-04-2021 - 12:39
 * project: SpringBoot
 *
 * @author dinar
 * @version v0.1
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final PasswordEncoder passwordEncoder;

    private final UsersRepository usersRepository;

    @Autowired
    public RegistrationServiceImpl(PasswordEncoder passwordEncoder,
                                   UsersRepository usersRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
    }

    @Override
    public User signup(UserSignupDto user) throws UserAlreadyExistException {

        if (usersRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistException();
        }

        String encodePass = passwordEncoder.encode(user.getPassword());

        User newUser = User.builder()
                .email(user.getEmail())
                .name(user.getName())
                .surname(user.getSurname())
                .password(encodePass)
                .role(User.Role.USER)
                .state(User.State.ACTIVE)
                .build();

        return usersRepository.saveAndFlush(newUser);
    }
}
