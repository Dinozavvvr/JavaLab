package ru.itis.javalab.utils;

import javax.servlet.http.Cookie;

/**
 * created: 19-11-2020 - 1:31
 * project: 07. Fremarker
 *
 * @author dinar
 * @version v0.1
 */
public class CookieUtils {
    public static Cookie getCookie(Cookie[] cookies, String name) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }
}
