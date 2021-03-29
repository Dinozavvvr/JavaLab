package ru.itis.javalab.services;

import ru.itis.javalab.dto.PostForm;
import ru.itis.javalab.models.Post;
import ru.itis.javalab.models.User;

import java.util.List;

/**
 * created: 21-02-2021 - 14:58
 * project: SemesterWork
 *
 * @author dinar
 * @version v0.1
 */
public interface PostService {

    List<Post> getPostsByUser(User user);

    public void savePost(PostForm postForm, User user);


}
