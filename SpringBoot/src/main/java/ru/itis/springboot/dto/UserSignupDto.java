package ru.itis.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * created: 18-04-2021 - 12:41
 * project: SpringBoot
 *
 * @author dinar
 * @version v0.1
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSignupDto {

    private String email;

    private String name;

    private String surname;

    private String password;

}
