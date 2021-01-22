package ru.itis.javalab.servlets;

import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.itis.javalab.models.Post;
import ru.itis.javalab.services.PostService;
import ru.itis.javalab.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

/**
 * created: 17-11-2020 - 20:36
 * project: 07. Freemarker
 *
 * @author dinar
 * @version v0.1
 */

@WebServlet("/posting")
public class PostServlet extends HttpServlet {

    private final String rootPath = "C:\\Users\\dinar\\Documents\\" +
            "1-All Projects\\#Java\\JavaLab\\JavaLab\\07. Fremarker\\store\\images\\";

    private final File file = new File("C:\\Users\\dinar\\Documents" +
            "\\1-All Projects\\#Java\\JavaLab\\JavaLab\\07. Fremarker\\src\\main\\webapp\\ftlh");

    private PostService postService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        this.postService = (PostService) context.getAttribute("postService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_20);
        configuration.setDefaultEncoding("UTF-8");

        configuration.setTemplateLoader(new FileTemplateLoader(file));
        Template template = configuration.getTemplate("newpost.ftlh");
        Map<String, Object> data = new HashMap<>();

        Optional<Post> examplePost = postService.getByFileName("examplePost");

        examplePost.ifPresent(post -> data.put("examplePost", post));

        try {
            template.setAutoFlush(true);
            template.process(data, response.getWriter());
        } catch (TemplateException e) {
            throw new IllegalStateException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        System.out.println(request.getParameter("place"));

        if (isMultipart) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                List<FileItem> items = upload.parseRequest(request);

                String fileName = getUniqueName();

                File image = new File(rootPath + fileName + ".jpg");

                items.get(0).write(image);

                System.out.println(items.get(0).getName());

                String place = items.get(1).getString().trim();
                String description = items.get(2).getString().trim();
                String person = items.get(3).getString().trim();

                System.out.println(place);
                System.out.println(description);
                System.out.println(person);

                HttpSession session = request.getSession();
                Long id = (Long) session.getAttribute("user_id");

                Post post = Post.builder()
                        .userId(id)
                        .time(new Timestamp(System.currentTimeMillis()))
                        .place(place)
                        .description(description)
                        .people(person)
                        .fileName(fileName)
                        .build();

                postService.savePost(post);

                response.sendRedirect("/account");
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
    }

    private String getUniqueName() {
        String name;

        do {
            name = String.valueOf(UUID.randomUUID());
        } while (postService.getByFileName(name).isPresent());

        return name;
    }
}
