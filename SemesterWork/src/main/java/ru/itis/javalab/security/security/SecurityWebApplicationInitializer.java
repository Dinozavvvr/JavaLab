package ru.itis.javalab.security.security;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.multipart.support.MultipartFilter;

import javax.servlet.ServletContext;

/**
 * created: 21-03-2021 - 20:59
 * project: SemesterWork
 *
 * @author dinar
 * @version v0.1
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    /* multipart forbidden error solution */
    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
        insertFilters(servletContext, new MultipartFilter());
    }
}
