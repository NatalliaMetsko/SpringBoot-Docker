package com.netcracker.metsko.dao.implementation;

import com.netcracker.metsko.dao.GenericDao;
import com.netcracker.metsko.databasemanager.DatabaseManager;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;

public class GenericDaoImpl<T, Long extends Serializable> implements GenericDao<T, Long> {

    protected EntityManager entityManager= getEntityManager();
    protected EntityTransaction tx = entityManager.getTransaction();

    private Class<T> tClass;



    public GenericDaoImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.tClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    @Override
    public void create(T newObject) throws SQLException {
        tx.begin();
        entityManager.persist(newObject);
        tx.commit();

    }



    @Override
    public T update(final T objectToUpdate) throws SQLException {
        tx.begin();
        T t=entityManager.merge(objectToUpdate);
        tx.commit();
        return t;

    }

    @Override
    public T read(Long id) throws SQLException {
        return this.entityManager.find(tClass, id);
    }

    @Override
    public void delete(T objectToDelete) throws SQLException {
        tx.begin();
        entityManager.remove(objectToDelete);
        tx.commit();
    }

    public EntityManager getEntityManager() {
        return DatabaseManager.getInstance();
    }

    public void close()
    {
        entityManager.close();
    }
}
