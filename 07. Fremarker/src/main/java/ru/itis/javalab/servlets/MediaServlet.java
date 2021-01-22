package ru.itis.javalab.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * MediaServlet
 * created: 22-01-2021 - 21:19
 * project: 07. Fremarker
 *
 * @author dinar
 * @version v0.1
 */
@WebServlet("/media")
public class MediaServlet extends HttpServlet {

    private final String imagesPath = "C:\\Users\\dinar\\Documents\\1-All Projects" +
            "\\#Java\\JavaLab\\JavaLab\\07. Fremarker\\store\\images\\";


    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");

        String path = request.getParameter("path");
        File file = new File(imagesPath + path + ".jpg");

        InputStream inputStream = new FileInputStream(file);
        response.getOutputStream().write(inputStream.readAllBytes());
    }
}
