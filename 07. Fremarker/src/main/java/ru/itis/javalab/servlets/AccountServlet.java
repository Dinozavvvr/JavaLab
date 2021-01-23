package ru.itis.javalab.servlets;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import ru.itis.javalab.models.Post;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.PostService;
import ru.itis.javalab.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * created: 18-11-2020 - 13:38
 * project: 07. Freemarker
 *
 * @author dinar
 * @version v0.1
 */
@WebServlet("/account")
public class AccountServlet extends HttpServlet {

    private UserService userService;

    private PostService postService;

    private final File file = new File("C:\\Users\\dinar\\Documents" +
            "\\1-All Projects\\#Java\\JavaLab\\JavaLab\\07. Fremarker\\src\\main\\webapp\\ftlh");

    private final String imagesPath = "C:/Users/dinar/Documents/1-All Projects" +
            "/#Java/JavaLab/JavaLab/07. Fremarker/store/images/";


    @Override
    public void init(ServletConfig config) {
        ServletContext servletContext = config.getServletContext();
        this.userService = (UserService) servletContext.getAttribute("userService");
        this.postService = (PostService) servletContext.getAttribute("postService");
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_20);
        configuration.setDefaultEncoding("UTF-8");

        configuration.setTemplateLoader(new FileTemplateLoader(file));
        Template template = configuration.getTemplate("account.ftlh");
        Map<String, Object> data = new HashMap<>();

        Long id = (Long) request.getSession().getAttribute("user_id");
        Optional<User> user = userService.getById(id);

        user.ifPresent(usr -> {
            List<Post> posts;
            posts = postService.getAllPostById(id);

            data.put("posts", posts);
            data.put("login", usr.getLogin());
            data.put("email", usr.getEmail());
        });

        try {
            template.setAutoFlush(true);
            template.process(data, response.getWriter());
        } catch (TemplateException e) {
            throw new IllegalStateException(e);
        }
    }
}
