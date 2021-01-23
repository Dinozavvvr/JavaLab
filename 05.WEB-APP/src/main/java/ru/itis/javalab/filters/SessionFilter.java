package ru.itis.javalab.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * SessionFilter
 * created: 23-01-2021 - 0:08
 * project: 05.WEB-APP
 *
 * @author dinar
 * @version v0.1
 */

public class SessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // correct uri checking
        if (checkURI(request.getRequestURI())) {
            filterChain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession();
        String isValid = (String) session.getAttribute("isValid");

        if (isValid != null) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }

    private boolean checkURI(String requestURI) {
        return requestURI.equals("/login");
    }

    @Override
    public void destroy() {

    }
}
