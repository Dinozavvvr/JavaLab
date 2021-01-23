package ru.itis.javalab.repositories;

import java.util.List;
import java.util.Optional;

/**
 * CrudRepository
 * created: 23-01-2021 - 20:41
 * project: 11.CSRF
 *
 * @author dinar
 * @version v0.1
 */

public interface CrudRepository<ID, T> {

    ID save(T entity);

    Optional<T> findById(ID id);

    List<T> findAll();

    void delete(T entity);

    void update(T entity);

}
