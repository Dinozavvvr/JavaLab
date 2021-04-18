package ru.itis.springboot.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.springboot.dto.UserLoginDto;
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
public class LoginServiceImpl implements LoginService {

    private final PasswordEncoder passwordEncoder;

    private final UsersRepository usersRepository;

    public LoginServiceImpl(PasswordEncoder passwordEncoder,
                            UsersRepository usersRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
    }

    @Override
    public User login(UserLoginDto user) {

        User loginUser = usersRepository.findByEmail(user.getEmail())
                .orElseThrow(() ->
                        new UsernameNotFoundException("user with this email not found"));

        return passwordEncoder
                .matches(user.getPassword(), loginUser.getPassword()) ? loginUser : null;
    }
}
