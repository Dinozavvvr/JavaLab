package ru.itis.javalab.repositories;

import java.util.List;

/**
 * created: 18-11-2020 - 22:18
 * project: 07. Fremarker
 *
 * @author dinar
 * @version v0.1
 */
public interface CrudRepository<T, ID> {

    ID save(T entity);

    List<T> findAll();

    void update(T entity);

    void delete(T entity);

}
