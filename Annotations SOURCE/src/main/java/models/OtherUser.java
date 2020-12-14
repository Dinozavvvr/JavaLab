package models;

import annotations.HtmlForm;
import annotations.HtmlInput;

/**
 * OtherUser
 * created: 09-12-2020 - 20:14
 * project: Annotations SOURCE
 *
 * @author dinar
 * @version v0.1
 */
@HtmlForm(action = "/otherUsers")
public class OtherUser {

    @HtmlInput(name = "nickname", placeholder = "your nickname")
    private String nickname;

    @HtmlInput(name = "email", type = "email", placeholder = "your email")
    private String email;

    @HtmlInput(name = "password", type="password", placeholder = "your password")
    private String password;

    @HtmlInput(name = "password", type="password", placeholder = "your other password")
    private final int value = 1;
}