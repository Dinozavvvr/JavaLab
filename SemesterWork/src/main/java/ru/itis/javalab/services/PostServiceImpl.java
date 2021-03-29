package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.javalab.dto.PostForm;
import ru.itis.javalab.models.Post;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.PostRepository;
import ru.itis.javalab.utils.Media;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * created: 21-02-2021 - 16:22
 * project: SemesterWork
 *
 * @author dinar
 * @version v0.1
 */
@Service
public class PostServiceImpl implements PostService {

    @Value("${media.images}")
    private String imagesPath;

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getPostsByUser(User user) {
        return postRepository.findAllByUserId(user.getId());
    }

    @Override
    public void savePost(PostForm postForm, User user) {
        String fileName;
        do {
            fileName = UUID.randomUUID().toString();
        } while (postRepository.findByFileName(fileName).isPresent());

        System.out.println();

        Post post = Post.builder()
                .people(postForm.getPeople())
                .place(postForm.getPlace())
                .description(postForm.getDescription())
                .fileName(fileName)
                .user(user)
                .time(new Timestamp(System.currentTimeMillis()))
                .build();

        postRepository.save(post);

        File file = new File(imagesPath + fileName
                + Media.JPEG.value);


        try {
            postForm.getFile().transferTo(file);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
