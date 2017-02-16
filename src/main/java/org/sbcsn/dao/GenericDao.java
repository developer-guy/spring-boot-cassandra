package org.sbcsn.dao;

import java.util.List;

/**
 * Created by bapaydin on 16.02.2017.
 */
public interface GenericDao<T,K> {
    T create(T t);
    T update(T t);
    void delete(T t);
    List<T> findAll();
    T findById(K id);
}
