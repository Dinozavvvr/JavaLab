package ru.itis.springboot.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.springboot.models.User;
import ru.itis.springboot.repositories.UsersRepository;

/**
 * created: 18-04-2021 - 13:01
 * project: SpringBoot
 *
 * @author dinar
 * @version v0.1
 */
@Service(value = "customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    public UserDetailsServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = usersRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("user with this email not found"));

        return new UserDetailsImpl(user);
    }
}
