package ru.itis.javalab.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;

/**
 * created: 21-10-2020 - 21:07
 * project: 05.WEB-APP
 *
 * @author dinar
 * @version v0.1
 */
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;

    public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
    }

    @Override
    public List<User> getAllByAge(Integer age) {
        return usersRepository.findAllByAge(age);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    @Override
    public void addUser(User user) {
        usersRepository.save(user);
    }

    @Override
    public String generateSecurePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean verifyPassword(String password, String securePassword) {
        return passwordEncoder.matches(password, securePassword);
    }
}
