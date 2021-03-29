package ru.itis.javalab.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Component
public class CsrfInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        if (request.getMethod().equals("GET")) {
            System.out.println("Method is GET");

            String csrf = String.valueOf(UUID.randomUUID());

            request.setAttribute("_csrf_token", csrf);
            Set<String> csrfSet = (Set<String>) request.getSession().getAttribute("_csrf_token_set");

            if (csrfSet == null) {
                csrfSet = new HashSet<>();
            }
            // add new csrf to session
            csrfSet.add(csrf);
            request.getSession().setAttribute("_csrf_token_set", csrfSet);

            return true;

        } else if (request.getMethod().equals("POST"))  {
            System.out.println("Method is POST");
            String requestCsrf = request.getParameter("_csrf_token");

            Set<String> csrfSet = (Set<String>) request.getSession().getAttribute("_csrf_token_set");

            for (String sessionCsrf : csrfSet) {
                if (sessionCsrf.equals(requestCsrf)) {
                    return true;
                }
            }

            response.setStatus(403);
            return false;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        if (request.getAttribute("_csrf_token") == null) {

            String csrf = String.valueOf(UUID.randomUUID());

            request.setAttribute("_csrf_token", csrf);
            Set<String> csrfSet = (Set<String>) request.getSession().getAttribute("_csrf_token_set");

            csrfSet.add(csrf);
            request.getSession().setAttribute("_csrf_token_set", csrfSet);
        }
    }
}