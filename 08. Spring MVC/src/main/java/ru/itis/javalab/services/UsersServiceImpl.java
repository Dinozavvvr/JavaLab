package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * UsersServiceImpl
 * created: 29-11-2020 - 21:25
 * project: 08. Spring MVC
 *
 * @author dinar
 * @version v0.1
 */

@Service(value = "UsersServiceImpl")
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Long login(String login, String password) {
        Optional<User> user = usersRepository.findByLogin(login);

        if (user.isPresent() && passwordEncoder
                .matches(password, user.get().getPassword())) {
            return user.get().getId();
        }

        return null;
    }

    @Override
    public Long signUp(User user) {
        if (usersRepository.findByEmail(user.getEmail()).isEmpty()
                && usersRepository.findByLogin(user.getLogin()).isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return usersRepository.save(user);
        }
        return null;
    }

    @Override
    public void rememberUser(HttpServletResponse response, Long userId) {

    }

    @Override
    public Long getUserId(String sessionId) {
        return null;
    }

    @Override
    public Optional<User> getById(Long id) {
        return usersRepository.findById(id);
    }
}
