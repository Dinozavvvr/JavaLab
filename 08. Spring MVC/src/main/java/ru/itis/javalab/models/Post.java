package ru.itis.javalab.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * Post
 * created: 29-11-2020 - 16:10
 * project: 08. Spring MVC
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
