package ru.itis.javalab.models;

import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private Boolean isDeleted;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.isDeleted = false;
    }
}
