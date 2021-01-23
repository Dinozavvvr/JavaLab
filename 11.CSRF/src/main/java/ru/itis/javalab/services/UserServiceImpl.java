package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;

import java.util.Optional;

/**
 * created: 23-01-2021 - 21:16
 * project: 11.CSRF
 *
 * @author dinar
 * @version v0.1
 */


@Service(value = "UserServiceImpl")
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void signUp(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        usersRepository.save(user);
    }

    @Override
    public void update(User user) {
        usersRepository.update(user);
    }

    @Override
    public boolean login(String username, String password) {
        Optional<User> user = usersRepository.findByUsername(username);

        return user.filter(value -> passwordEncoder.matches(password,
                value.getPassword())).isPresent();

    }

    @Override
    public Optional<User> getByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    @Override
    public Optional<User> getById(Long id) {
        return usersRepository.findById(id);
    }
}
