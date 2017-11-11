package com.netcracker.metsko.dao.implementation;

import com.netcracker.metsko.dao.GenericDao;
import com.netcracker.metsko.databasemanager.DatabaseManager;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.sql.SQLException;

public class GenericDaoImpl<T, ID extends Serializable> implements GenericDao<T, ID> {

    protected EntityManager entityManager= getEntityManager();
    private Class<T> tClass;

    public GenericDaoImpl() {
    }

    public GenericDaoImpl(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    public void create(T newObject) throws SQLException {
        if(!entityManager.contains(newObject)) {
            entityManager.persist(newObject);
        }
        else {System.out.println("Объект уже существует. \n");}
    }

    @Override
    public Object read(ID id) throws SQLException {
        return  entityManager.find(tClass, id);
    }

    @Override
    public Object update(T objectToUpdate) throws SQLException {
        if(entityManager.contains(objectToUpdate)) {
            return this.entityManager.merge(objectToUpdate);
        }
        else {
            entityManager.persist(objectToUpdate);
            return entityManager.find(tClass, objectToUpdate);
        }
    }


    @Override
    public void delete(T objectToDelete) throws SQLException {
        if(entityManager.contains(objectToDelete)) {
            entityManager.remove(objectToDelete);
        }
        else {System.out.println("Объект не существует.\n");}
    }

    private EntityManager getEntityManager() {
        return DatabaseManager.getInstance();
    }
}
