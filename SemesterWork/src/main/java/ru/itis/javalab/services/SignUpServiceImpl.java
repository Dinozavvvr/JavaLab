package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.UserSignUpForm;
import ru.itis.javalab.models.Role;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.RoleRepository;
import ru.itis.javalab.repositories.UserRepository;
import ru.itis.javalab.utils.EmailUtil;
import ru.itis.javalab.utils.MailsGenerator;

import java.util.*;
import java.util.function.Supplier;

/**
 * created: 20-02-2021 - 23:28
 * project: SemesterWork
 *
 * @author dinar
 * @version v0.1
 */
@Service
public class SignUpServiceImpl implements SignUpService {

    @Value("${server.url}")
    private String serverUrl;

    @Value("${spring.mvc.mail.username}")
    private String fromEmail;

    @Value("${mail.confirm.code.subject}")
    private String emailSubject;

    private final MailsGenerator mailsGenerator;

    private final EmailUtil emailUtil;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    @Autowired
    public SignUpServiceImpl(MailsGenerator mailsGenerator,
                             UserRepository userRepository,
                             EmailUtil emailUtil,
                             PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.mailsGenerator = mailsGenerator;
        this.userRepository = userRepository;
        this.emailUtil = emailUtil;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public User signUp(UserSignUpForm userSignUpForm) {
        Optional<User> optionalUser = userRepository.findByEmail(userSignUpForm.getEmail());

        if (optionalUser.isEmpty()) {

            Role role = roleRepository.findByRole(Role.Roles.USER)
                    .orElseThrow(IllegalStateException::new);

            User user = User.builder()
                    .email(userSignUpForm.getEmail())
                    .login(userSignUpForm.getLogin())
                    .password(passwordEncoder.encode(userSignUpForm.getPassword()))
                    .confirmCode(UUID.randomUUID().toString())
                    .state(User.State.NOT_CONFIRMED)
                    .accountState(User.AccountState.ACTIVE)
                    .roles(Collections.singletonList(role))
                    .subscribers(0)
                    .subscriptions(0)
                    .build();

            role.getUsers().add(user);

            String confirmMailMessage = mailsGenerator.getMailForConfirm(serverUrl,
                    user.getConfirmCode());

            emailUtil.sendEmail(user.getEmail(), emailSubject, fromEmail, confirmMailMessage);

            return userRepository.saveAndFlush(user);
        }
        return null;
    }

    @Override
    public User.State confirmUser(String code) {
        Optional<User> optionalUser = userRepository.findByConfirmCode(code);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (user.getState().equals(User.State.CONFIRMED)) {
                return User.State.CONFIRMED;
            } else {
                user.setState(User.State.NOT_CONFIRMED);
                userRepository.save(user);
                return User.State.NOT_CONFIRMED;
            }
        }

        return null;
    }
}
