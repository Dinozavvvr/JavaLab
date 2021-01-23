package ru.itis.javalab.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserCookie
 * created: 29-11-2020 - 16:08
 * project: 08. Spring MVC
 *
 * @author dinar
 * @version v0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCookie {

    private Long userId;

    private String sessionId;

}
