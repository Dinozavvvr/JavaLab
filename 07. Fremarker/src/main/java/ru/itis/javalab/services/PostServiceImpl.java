package ru.itis.javalab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.itis.javalab.models.Post;
import ru.itis.javalab.repositories.PostRepository;

import java.util.List;
import java.util.Optional;

/**
 * created: 19-11-2020 - 0:47
 * project: 07. Fremarker
 *
 * @author dinar
 * @version v0.1
 */

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(@Qualifier("PostRepositoryJdbcTemplateImpl")
                                       PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void savePost(Post post) {
        postRepository.save(post);
    }

    @Override
    public Optional<Post> getByFileName(String fileName) {
        return postRepository.findByFileName(fileName);
    }

    @Override
    public List<Post> getAllPostById(Long id) {
        return postRepository.findAllByUserId(id);
    }
}
