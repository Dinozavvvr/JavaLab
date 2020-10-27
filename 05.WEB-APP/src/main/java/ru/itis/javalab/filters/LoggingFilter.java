package ru.itis.javalab.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * created: 21-10-2020 - 12:10
 * project: 05.WEB-APP
 *
 * @author dinar
 * @version v0.1
 */
@WebFilter("/*")
public class LoggingFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

//    127.0.0.1 user-identifier john [20/Jan/2020:21:32:14 -0700] "GET /apache_pb.gif HTTP/1.0" 200 4782
//    host ident authuser date request status bytes

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        log.info("{} \"{} {}\"",request.getRemoteAddr(), request.getMethod(), request.getRequestURL());

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
