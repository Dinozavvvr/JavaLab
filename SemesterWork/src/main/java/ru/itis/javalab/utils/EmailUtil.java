package ru.itis.javalab.utils;

public interface EmailUtil {

    void sendEmail(String to, String subject, String from, String text);

}
