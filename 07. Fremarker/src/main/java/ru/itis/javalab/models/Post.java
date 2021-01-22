package ru.itis.javalab.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * created: 18-11-2020 - 22:21
 * project: 07. Fremarker
 *
 * @author dinar
 * @version v0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    private Long id;

    private Long userId;

    private Timestamp time;

    private String place;

    private String description;

    private String people;

    private String fileName;

}
