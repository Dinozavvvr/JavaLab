package ru.itis.javalab.listeners;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
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

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.Properties;

/**
 * created: 21-10-2020 - 18:25
 * project: 05.WEB-APP
 *
 * @author dinar
 * @version v0.1
 */
@WebListener
public class CustomContextServletListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        Properties properties = new Properties();
        try {
            properties.load(servletContext.getResourceAsStream("/WEB-INF/properties/db.properties"));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(properties.getProperty("db.jdbc.url"));
        hikariConfig.setDriverClassName(properties.getProperty("db.jdbc.driver.classname"));
        hikariConfig.setUsername(properties.getProperty("db.jdbc.username"));
        hikariConfig.setPassword(properties.getProperty("db.jdbc.password"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.jdbc.hikari.max-pool-size")));


        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        CookiesRepository cookiesRepository = new CookiesRepositoryJdbcImpl(dataSource);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        UsersService usersService = new UsersServiceImpl(usersRepository, passwordEncoder);
        UserCookiesService userCookiesService = new UserCookiesServiceImpl(cookiesRepository);

        servletContext.setAttribute("usersService", usersService);
        servletContext.setAttribute("userCookiesService", userCookiesService);


    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
