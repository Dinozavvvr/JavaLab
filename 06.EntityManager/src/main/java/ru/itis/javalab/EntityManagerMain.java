package ru.itis.javalab;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.itis.javalab.model.User;
import ru.itis.javalab.services.EntityManager;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Properties;

/**
 * created: 11-11-2020 - 20:35
 * project: 06.EntityManager
 *
 * @author dinar
 * @version v0.1
 */
public class EntityManagerMain {
    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/main/resources/db.properties"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(properties.getProperty("db.jdbc.url"));
        config.setUsername(properties.getProperty("db.jdbc.user"));
        config.setPassword(properties.getProperty("db.jdbc.password"));
        config.setDriverClassName(properties.getProperty("db.jdbc.driver.classname"));
        config.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.jdbc.hikari.max-pool-size")));

        HikariDataSource dataSource = new HikariDataSource(config);
        try {
            System.out.println(dataSource.getConnection().toString());
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

        EntityManager entityManager = new EntityManager(dataSource);

//        entityManager.createTable("users", User.class);

        User user = new User(1L, "Dinar", "Shgaliev", true);
//        entityManager.save("users", user);

        User newUser = entityManager.findById("users", User.class, Long.class, 1L);
        System.out.println(newUser.toString());
    }
}
