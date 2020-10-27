package ru.itis.javalab.filters;

import ru.itis.javalab.services.UserCookiesService;
import ru.itis.javalab.services.UsersService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * created: 21-10-2020 - 20:24
 * project: 05.WEB-APP
 *
 * @author dinar
 * @version v0.1
 */
@WebFilter("/*")
public class AuthFilter implements Filter {

    private static final String AUTH = "auth";

    UserCookiesService userCookiesService;
    UsersService usersService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        userCookiesService = (UserCookiesService) servletContext.getAttribute("userCookiesService");
        usersService = (UsersService) servletContext.getAttribute("usersService");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Cookie[] cookies = request.getCookies();
        String auth = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(AUTH)) {
                    auth = cookie.getValue();
                }
            }
        }

        if (auth == null || !userCookiesService.getUserCookieByAuth(auth).isPresent()) {
            if(!request.getRequestURI().equals("/login")) {
                response.sendRedirect("/login");
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else  {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
