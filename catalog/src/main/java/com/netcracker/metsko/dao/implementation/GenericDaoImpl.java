package com.netcracker.metsko.dao.implementation;

import com.netcracker.metsko.dao.GenericDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;


public class GenericDaoImpl<T, Long extends Serializable> implements GenericDao<T, Long> {

    @PersistenceContext
    public EntityManager entityManager;


    private final Class<T> tClass;

    public GenericDaoImpl(){
    ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.tClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
}

    @Override
    public void create(T newObject) throws SQLException {
        try {
            if (entityManager.contains(newObject)) {
                System.out.println("The object exists in DB\n");
            } else {
                entityManager.getTransaction().begin();
                entityManager.persist(newObject);
                entityManager.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();

            if (entityManager.getTransaction() != null) {
                try {

                    System.err.print("Transaction is being rolled back");
                    entityManager.getTransaction().rollback();
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
                    entityManager.getTransaction().begin();
                    T t = entityManager.merge(objectToUpdate);
                    entityManager.getTransaction().commit();
                    return t;
                }
                else {
                    System.err.println("There is no such object in DB\n");
                }
            }catch (Exception e)
            {
                e.printStackTrace();
                if (entityManager.getTransaction() != null){
                    try {
                        System.err.print("Transaction is being rolled back\n");
                        entityManager.getTransaction().rollback();
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
    public void delete(Long id) throws SQLException {
        try {
            if(this.entityManager.find(tClass, id)!=null) {
                entityManager.getTransaction().begin();
                entityManager.remove(this);
                entityManager.getTransaction().commit();
            }
            else {
                System.err.println("There is no such object in DB\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (entityManager.getTransaction() != null) {
                try {
                    System.err.println("Transaction is being rolled back\n");
                    entityManager.getTransaction().rollback();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

//    public EntityManager getEntityManager() {
//        return DatabaseManager.getInstance();
//    }

    public void close()
    {
        entityManager.close();
    }
}
