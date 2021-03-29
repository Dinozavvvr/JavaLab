package ru.itis.javalab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.models.Role;

import java.util.Optional;

/**
 * created: 21-03-2021 - 22:32
 * project: SemesterWork
 *
 * @author dinar
 * @version v0.1
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRole(Role.Roles role);

}
