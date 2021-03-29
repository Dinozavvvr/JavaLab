package ru.itis.javalab.utils;

/**
 * created: 21-02-2021 - 16:00
 * project: SemesterWork
 *
 * @author dinar
 * @version v0.1
 */
public enum Cookies {

    SESSION_ID("session_id");

    public final String value;

    Cookies(String label) {
        this.value = label;
    }
}
