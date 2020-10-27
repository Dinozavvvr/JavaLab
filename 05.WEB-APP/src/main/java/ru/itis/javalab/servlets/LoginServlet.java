package ru.itis.javalab.servlets;

import ru.itis.javalab.models.User;
import ru.itis.javalab.services.UserCookiesService;
import ru.itis.javalab.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * created: 21-10-2020 - 21:04
 * project: 05.WEB-APP
 *
 * @author dinar
 * @version v0.1
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    UserCookiesService userCookiesService;
    UsersService usersService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        ServletContext servletContext = servletConfig.getServletContext();
        userCookiesService = (UserCookiesService) servletContext.getAttribute("userCookiesService");
        usersService = (UsersService) servletContext.getAttribute("usersService");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/login.html").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Optional<User> user = usersService.getByUsername(username);
        if(user.isPresent() && usersService.verifyPassword(password, user.get().getPassword())) {
            userCookiesService.addCookie(user.get().getId(), response);
            response.sendRedirect("/profile");
        } else {
            response.sendRedirect("/login");
        }
    }
}
