package ru.itis.javalab.repositories;

import ru.itis.javalab.models.Post;

import java.util.List;
import java.util.Optional;

/**
 * created: 18-11-2020 - 22:27
 * project: 07. Fremarker
 *
 * @author dinar
 * @version v0.1
 */

public interface PostRepository extends CrudRepository<Post, Long> {

    List<Post> findAllByUserId(Long id);

    Optional<Post> findById(Long id);

    Optional<Post> findByFileName(String fileName);
}
