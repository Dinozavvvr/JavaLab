package ru.itis.springboot.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * created: 18-04-2021 - 12:27
 * project: SpringBoot
 *
 * @author dinar
 * @version v0.1
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "email", name = "email_uniq")
        })
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String  password;

    @Column(length = 20)
    private String name;

    @Column(length = 30)
    private String surname;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public enum State {
        BANNED, ACTIVE, DELETED
    }

    public enum Role {
        USER, ADMIN
    }

    public boolean isActive() {
        return state == State.ACTIVE;
    }

    public boolean isBanned() {
        return state == State.BANNED;
    }

    public boolean isDeleted() {
        return state == State.DELETED;
    }
}
