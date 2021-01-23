package ru.itis.javalab.services;

import ru.itis.javalab.models.Post;

import java.util.List;
import java.util.Optional;

/**
 * created: 19-11-2020 - 0:46
 * project: 07. Fremarker
 *
 * @author dinar
 * @version v0.1
 */
public interface PostService {

    void savePost(Post post);

    Optional<Post> getByFileName(String fileName);

    List<Post> getAllPostById(Long id);

}
