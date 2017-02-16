package org.sbcsn.service;

import org.sbcsn.dao.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bapaydin on 16.02.2017.
 */

@Service
public abstract class GenericServiceImpl<T,K extends Serializable> implements GenericService<T,K> {

    @Autowired
    private GenericDao<T,K> genericDao;

    @Override
    public T create(T t) {
        return genericDao.create(t);
    }

    @Override
    public T update(T t) {
        return genericDao.update(t);
    }

    @Override
    public void delete(T t) {
        genericDao.delete(t);
    }

    @Override
    public List<T> findAll() {
        return genericDao.findAll();
    }

    @Override
    public T findById(K id) {
        return genericDao.findById(id);
    }
}
