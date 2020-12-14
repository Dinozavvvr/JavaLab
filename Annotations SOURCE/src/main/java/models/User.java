package models;

import annotations.HtmlForm;
import annotations.HtmlInput;

/**
 * User
 * created: 09-12-2020 - 17:43
 * project: Annotations SOURCE
 *
 * @author dinar
 * @version v0.1
 */


@HtmlForm(method = "post", action = "/users")
public class User {

    @HtmlInput(name = "nickname", placeholder = "your nickname")
    private String nickname;

    @HtmlInput(name = "email", type = "email", placeholder = "your email")
    private String email;

    @HtmlInput(name = "password", type="password", placeholder = "your password")
    private String password;

    private int value;
}
