package ru.itis.javalab.repositories;

import ru.itis.javalab.models.Post;

import java.util.List;
import java.util.Optional;

/**
 * created: 28-02-2021 - 17:39
 * project: SemesterWork
 *
 * @author dinar
 * @version v0.1
 */
public interface CrudPostRepository extends CrudRepository<Post, Long> {

    List<Post> findAllByUserId(Long id);

    Optional<Post> findById(Long id);

    Optional<Post> findByFileName(String fileName);
}
