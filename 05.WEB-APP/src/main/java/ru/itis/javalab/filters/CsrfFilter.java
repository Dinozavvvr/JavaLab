package ru.itis.javalab.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * CsrfFilter
 * created: 23-01-2021 - 15:35
 * project: 05.WEB-APP
 *
 * @author dinar
 * @version v0.1
 */

public class CsrfFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getMethod().equals("GET")) {
            String csrf = String.valueOf(UUID.randomUUID());

            request.setAttribute("_csrf_token", csrf);
            Set<String> csrfSet = (Set<String>) request.getSession().getAttribute("_csrf_token_set");

            if (csrfSet == null) {
                csrfSet = new HashSet<>();
            }
            // add new csrf to session
            csrfSet.add(csrf);
            request.getSession().setAttribute("_csrf_token_set", csrfSet);

            filterChain.doFilter(request, response);

        } else if (request.getMethod().equals("POST"))  {
            String requestCsrf = request.getParameter("_csrf_token");

            Set<String> csrfSet = (Set<String>) request.getSession().getAttribute("_csrf_token_set");

            for (String sessionCsrf : csrfSet) {
                if (sessionCsrf.equals(requestCsrf)) {
                    filterChain.doFilter(request, response);
                }
            }

            response.setStatus(403);
        }
    }

    @Override
    public void destroy() {

    }
}
