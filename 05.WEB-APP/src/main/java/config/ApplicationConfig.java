package config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.javalab.repositories.CookiesRepository;
import ru.itis.javalab.repositories.CookiesRepositoryJdbcImpl;
import ru.itis.javalab.repositories.UsersRepository;
import ru.itis.javalab.repositories.UsersRepositoryJdbcImpl;
import ru.itis.javalab.services.UserCookiesService;
import ru.itis.javalab.services.UserCookiesServiceImpl;
import ru.itis.javalab.services.UsersService;
import ru.itis.javalab.services.UsersServiceImpl;

import javax.sql.DataSource;

/**
 * created: 05-11-2020 - 0:34
 * project: 05.WEB-APP
 *
 * @author dinar
 * @version v0.1
 */

@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan(basePackages = {"ru.itis.javalab"})
public class ApplicationConfig {

    @Autowired
    private Environment environment;

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(environment.getProperty("db.jdbc.url"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(environment.getProperty("db.jdbc.hikari.max-pool-size")));
        hikariConfig.setUsername(environment.getProperty("db.jdbc.username"));
        hikariConfig.setPassword(environment.getProperty("db.jdbc.password"));
        hikariConfig.setDriverClassName(environment.getProperty("db.jdbc.driver.classname"));
        return hikariConfig;
    }

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UsersRepository usersRepository() {
        return new UsersRepositoryJdbcImpl(jdbcTemplate());
    }

    @Bean
    public UsersService usersService() {
        return new UsersServiceImpl(usersRepository(), passwordEncoder());
    }

    @Bean
    public CookiesRepository cookiesRepository() {
        return new CookiesRepositoryJdbcImpl(jdbcTemplate());
    }

    @Bean
    public UserCookiesService userCookiesService() {
        return new UserCookiesServiceImpl(cookiesRepository());
    }
}
