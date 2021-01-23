package ru.itis.javalab.servlets;

import com.sun.net.httpserver.HttpContext;
import org.springframework.context.ApplicationContext;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.UserCookiesService;
import ru.itis.javalab.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

/**
 * DeleteAccountServlet
 * created: 23-01-2021 - 14:57
 * project: 05.WEB-APP
 *
 * @author dinar
 * @version v0.1
 */

@WebServlet("/deleteAccount")
public class DeleteAccountServlet extends HttpServlet {

    private UsersService usersService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");
        usersService = applicationContext.getBean(UsersService.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("session_id")) {
                // removing cookie from browser
                cookie.setMaxAge(10);
            }
        }

        Long userId = (Long) session.getAttribute("user_id");

        System.out.println(userId);
        Optional<User> user = usersService.getById(userId);

        // set is_deleted TRUE
        user.ifPresent(usr -> {
            usr.setIsDeleted(true);
            usersService.update(usr);
        });

        // remove session
        session.invalidate();

        response.sendRedirect("/login");
    }
}
