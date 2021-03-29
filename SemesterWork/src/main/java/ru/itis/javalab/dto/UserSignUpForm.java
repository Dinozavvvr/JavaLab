package ru.itis.javalab.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
public class UserSignUpForm {

    @NotEmpty(message = "")
    @Pattern(regexp = "[A-Za-z0-9]{4,8}", message = "")
    private String login;

    @NotEmpty(message = "")
    @Email(message = "")
    private String email;

    @NotEmpty(message = "")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "")
    private String password;

}
