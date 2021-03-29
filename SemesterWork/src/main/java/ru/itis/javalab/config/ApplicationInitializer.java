package ru.itis.javalab.config;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
import ru.itis.javalab.security.security.SecurityConfiguration;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.io.IOException;

/**
 * created: 18-02-2021 - 22:39
 * project: SemesterWork
 *
 * @author dinar
 * @version v0.1
 */
public class ApplicationInitializer implements WebApplicationInitializer{

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext springWebContext
                = new AnnotationConfigWebApplicationContext();

//        PropertySource propertySource;
//        try {
//            propertySource = new ResourcePropertySource("classpath:application.properties");
//        } catch (IOException e) {
//            throw new IllegalStateException(e);
//        }
//        springWebContext
//                .getEnvironment()
//                .setActiveProfiles((String) propertySource.getProperty("spring.profile"));

        springWebContext.register(ApplicationConfig.class);
        springWebContext.register(SecurityConfiguration.class);

        servletContext.addListener(new ContextLoaderListener(springWebContext));

        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        FilterRegistration.Dynamic filterRegistration = servletContext
                .addFilter("characterEncodingFilter", characterEncodingFilter);
        filterRegistration.addMappingForUrlPatterns(null, false, "/*");

        ServletRegistration.Dynamic dispatcherServlet =
                servletContext.addServlet("dispatcher",
                        new DispatcherServlet(springWebContext));

        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/");
    }
}
