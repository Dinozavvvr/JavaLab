package ru.itis.javalab.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User
 * created: 23-01-2021 - 20:39
 * project: 11.CSRF
 *
 * @author dinar
 * @version v0.1
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    private Long id;
    
    private String username;
    
    private String firstName;
    
    private String lastName;

    private String password;
    
    private Boolean isDeleted;
    
}
