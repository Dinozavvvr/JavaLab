package ru.itis.javalab.filters;

import ru.itis.javalab.services.UserService;
import ru.itis.javalab.utils.*;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * created: 19-11-2020 - 1:23
 * project: 07. Fremarker
 *
 * @author dinar
 * @version v0.1
 */

@WebFilter(urlPatterns = {"/*"})
public class GlobalFilter implements Filter {

    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        this.userService = (UserService) servletContext.getAttribute("userService");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession httpSession = httpServletRequest.getSession();

        if (httpSession.getAttribute("user_id") == null) {

            String sessionId = null;
            Cookie cookie = CookieUtils.getCookie(httpServletRequest.getCookies(),"session_id");
            if (cookie != null) {
                sessionId = cookie.getValue();
            }

            if (sessionId != null) {
                httpSession.setAttribute("user_id", userService.getUserId(sessionId));
            }
        }
        chain.doFilter(request, response);
    }
}
