package ru.itis.javalab.listeners;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.javalab.config.*;
import ru.itis.javalab.services.*;
import ru.itis.javalab.services.PostServiceImpl;
import ru.itis.javalab.services.UserService;
import ru.itis.javalab.services.UserServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * created: 19-11-2020 - 1:25
 * project: 07. Fremarker
 *
 * @author dinar
 * @version v0.1
 */

@WebListener
public class ApplicationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext servletContext = event.getServletContext();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        UserService userService = applicationContext.getBean(UserServiceImpl.class);
        PostService postService = applicationContext.getBean(PostServiceImpl.class);

        servletContext.setAttribute("userService", userService);
        servletContext.setAttribute("postService", postService);
    }
}
