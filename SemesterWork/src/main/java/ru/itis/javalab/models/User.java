package ru.itis.javalab.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * created: 20-02-2021 - 23:05
 * project: SemesterWork
 *
 * @author dinar
 * @version v0.1
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "\"user\"", uniqueConstraints = {
        @UniqueConstraint(columnNames = "login", name = "uniqueLogin"),
        @UniqueConstraint(columnNames = "email", name = "uniqueEmail")
})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 8)
    private String login;

    @Column(unique = true, nullable = false, length = 300)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "integer default 0")
    private Integer subscribers;

    @Column(columnDefinition = "integer default 0")
    private Integer subscriptions;

    private String confirmCode;

    @OneToMany(mappedBy = "user")
    private List<Post> post;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @Enumerated(value = EnumType.STRING)
    private AccountState accountState;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_has_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    public enum State {
        CONFIRMED, NOT_CONFIRMED    }

    public enum AccountState {
        ACTIVE, BANNED
    }

    public boolean isActive() {
        return accountState == AccountState.ACTIVE;
    }

    public boolean isBanned() {
        return accountState == AccountState.BANNED;
    }
}
