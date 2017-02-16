package org.sbcsn.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by bapaydin on 16.02.2017.
 */
@Repository(value = "genericDaoImpl")
public abstract class GenericDaoImpl<T, K extends Serializable> implements GenericDao<T, K> {


    @Autowired
    protected CassandraOperations cassandraOperations;

    private Class<? extends T> daoType;

    public GenericDaoImpl() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        daoType = (Class) parameterizedType.getActualTypeArguments()[0];
    }


    @Override
    public T create(T t) {
        return cassandraOperations.insert(t);
    }

    @Override
    public T update(T t) {
        return cassandraOperations.update(t);
    }

    @Override
    public void delete(T t) {
        cassandraOperations.delete(t);
    }

    @Override
    public List<T> findAll() {
        return cassandraOperations.selectAll((Class<T>) daoType);
    }

    @Override
    public T findById(K id) {
        return cassandraOperations.selectOneById(daoType,id);
    }
}
