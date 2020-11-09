package ru.itis.javalab.listeners;

import config.ApplicationConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

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
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        servletContext.setAttribute("applicationContext", applicationContext);
//
//        Properties properties = new Properties();
//        try {
//            properties.load(servletContext.getResourceAsStream("/WEB-INF/properties/db.properties"));
//        } catch (IOException e) {
//            throw new IllegalStateException(e);
//        }
//
//        HikariConfig hikariConfig = new HikariConfig();
//        hikariConfig.setJdbcUrl(properties.getProperty("db.jdbc.url"));
//        hikariConfig.setDriverClassName(properties.getProperty("db.jdbc.driver.classname"));
//        hikariConfig.setUsername(properties.getProperty("db.jdbc.username"));
//        hikariConfig.setPassword(properties.getProperty("db.jdbc.password"));
//        hikariConfig.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.jdbc.hikari.max-pool-size")));
//
//
//        HikariDataSource dataSource = new HikariDataSource(hikariConfig);
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(jdbcTemplate);
//        CookiesRepository cookiesRepository = new CookiesRepositoryJdbcImpl(jdbcTemplate);
//
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//        UsersService usersService = new UsersServiceImpl(usersRepository, passwordEncoder);
//        UserCookiesService userCookiesService = new UserCookiesServiceImpl(cookiesRepository);
//
//        servletContext.setAttribute("usersService", usersService);
//        servletContext.setAttribute("userCookiesService", userCookiesService);


    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
