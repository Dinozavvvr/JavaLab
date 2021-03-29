package ru.itis.javalab.services;

import org.springframework.stereotype.Service;
import ru.itis.javalab.models.Role;
import ru.itis.javalab.repositories.RoleRepository;

import javax.annotation.PostConstruct;
import java.util.Optional;

/**
 * created: 21-03-2021 - 22:46
 * project: SemesterWork
 *
 * @author dinar
 * @version v0.1
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    private void init() {
        Optional<Role> role = roleRepository.findByRole(Role.Roles.USER);
        if (role.isEmpty()) {
            roleRepository.save(Role.builder().role(Role.Roles.USER).build());
        }
        role = roleRepository.findByRole(Role.Roles.ADMIN);
        if (role.isEmpty()) {
            roleRepository.save(Role.builder().role(Role.Roles.ADMIN).build());
        }
    }
}
