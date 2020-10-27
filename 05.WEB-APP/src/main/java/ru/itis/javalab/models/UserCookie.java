package ru.itis.javalab.models;

import lombok.*;

/**
 * created: 21-10-2020 - 20:39
 * project: 05.WEB-APP
 *
 * @author dinar
 * @version v0.1
 */

@Builder
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserCookie {
    private Long userId;
    private String auth;
}
