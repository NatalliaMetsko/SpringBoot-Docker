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
        try {
            if (entityManager.contains(newObject)) {
                System.out.println("The object exists in DB\n");
            } else {
                tx.begin();
                entityManager.persist(newObject);
                tx.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();

            if (tx != null) {
                try {

                    System.err.print("Transaction is being rolled back");
                    tx.rollback();
                } catch (Exception exception) {

                    exception.printStackTrace();
                }
            }
        }
    }

    @Override
    public T update(final T objectToUpdate) throws SQLException {
            try {
                if(entityManager.contains(objectToUpdate)) {
                    tx.begin();
                    T t = entityManager.merge(objectToUpdate);
                    tx.commit();
                    return t;
                }
                else {
                    System.err.println("There is no such object in DB\n");
                }
            }catch (Exception e)
            {
                e.printStackTrace();
                if (tx != null){
                    try {
                        System.err.print("Transaction is being rolled back\n");
                        tx.rollback();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
            return objectToUpdate;
    }

    @Override
    public T read(Long id) throws SQLException {
        return this.entityManager.find(tClass, id);
    }

    @Override
    public void delete(T objectToDelete) throws SQLException {
        try {
            if(entityManager.contains(objectToDelete)) {

                tx.begin();
                entityManager.remove(objectToDelete);
                tx.commit();
            }
            else {
                System.err.println("There is no such object in DB\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                try {
                    System.err.println("Transaction is being rolled back\n");
                    tx.rollback();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    public EntityManager getEntityManager() {
        return DatabaseManager.getInstance();
    }

    public void close()
    {
        entityManager.close();
    }
}
