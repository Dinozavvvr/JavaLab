package ru.itis.javalab.repositories;

import java.util.List;
import java.util.Optional;

/**
 * base Create Read Update Delete Repository interface
 *
 * CrudRepository
 * created: 29-11-2020 - 15:27
 * project: 08. Spring MVC
 *
 * @author dinar
 * @version v0.1
 */
public interface CrudRepository<E, ID> {

    Optional<E> findById(ID id);

    List<E> findAll();

    void update(E entity);

    ID save(E entity);

    void delete(E entity);

}
