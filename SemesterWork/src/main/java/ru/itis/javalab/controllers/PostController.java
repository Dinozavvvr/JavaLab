package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.javalab.dto.PostForm;
import ru.itis.javalab.models.Post;
import ru.itis.javalab.security.details.UserDetailsImpl;
import ru.itis.javalab.services.PostService;
import ru.itis.javalab.services.UserService;

import javax.annotation.security.PermitAll;
import javax.servlet.annotation.MultipartConfig;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * created: 20-02-2021 - 23:13
 * project: SemesterWork
 *
 * @author dinar
 * @version v0.1
 */
@Controller
@MultipartConfig(location="/Storage", fileSizeThreshold=1024*1024,
        maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class PostController {

    @Value("${media.example.post.people}")
    private String people;

    @Value("${media.example.post.description}")
    private String description;

    @Value("${media.example.post.place}")
    private String place;

    @Value("${media.example.post.filename}")
    private String fileName;

    private final PostService postService;

    private final UserService userService;

    @Autowired
    public PostController(PostService postService,
                          UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/post")
    public ModelAndView getPostPage() {
        return new ModelAndView("newpost", writeTemplate());
    }

    @PostMapping("/post")
    public String handleFileUpload(@ModelAttribute PostForm postForm,
            @AuthenticationPrincipal UserDetailsImpl userDetails) throws UnsupportedEncodingException {
        postService.savePost(postForm, userDetails.getUser());
        return "redirect:/account";
    }

    private Map<String, Object> writeTemplate() {
        Map<String, Object> map = new HashMap<>();

        Post examplePost = Post.builder()
                .description(description)
                .fileName(fileName)
                .place(place)
                .people(people)
                .build();
        map.put("examplePost",examplePost);

        return map;
    }

}
