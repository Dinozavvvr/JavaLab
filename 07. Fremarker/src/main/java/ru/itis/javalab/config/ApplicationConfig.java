package ru.itis.javalab.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.javalab.repositories.PostRepositoryJdbcTemplateImpl;
import ru.itis.javalab.services.PostServiceImpl;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * created: 18-11-2020 - 22:37
 * project: 07. Fremarker
 *
 * @author dinar
 * @version v0.1
 */
@Configuration
@ComponentScan(basePackages = {"ru.itis.javalab"})
@PropertySource("classpath:db.properties")
public class ApplicationConfig {

    @Autowired
    private Environment environment;

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public SimpleJdbcInsert simpleJdbcInsert() {
        return new SimpleJdbcInsert(dataSource());
    }

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl(environment.getProperty("db.jdbc.url"));
        hikariConfig.setDriverClassName(environment.getProperty("db.jdbc.driver.classname"));
        hikariConfig.setUsername(environment.getProperty("db.jdbc.username"));
        hikariConfig.setPassword(environment.getProperty("db.jdbc.password"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(
                Objects.requireNonNull(environment.getProperty("db.jdbc.hikari.max-pool-size"))));

        return hikariConfig;
    }
}
