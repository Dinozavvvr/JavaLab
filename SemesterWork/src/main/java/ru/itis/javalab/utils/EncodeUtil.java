package ru.itis.javalab.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * created: 21-02-2021 - 22:07
 * project: SemesterWork
 *
 * @author dinar
 * @version v0.1
 */
public class EncodeUtil {

    String decodeText(String input, String encoding) throws IOException {
        return new BufferedReader(
                        new InputStreamReader(
                                new ByteArrayInputStream(input.getBytes()),
                                Charset.forName(encoding)))
                        .readLine();
    }

}
