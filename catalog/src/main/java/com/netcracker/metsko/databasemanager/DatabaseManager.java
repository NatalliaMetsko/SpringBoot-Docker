package com.netcracker.metsko.databasemanager;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DatabaseManager {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("CatalogPU");

    public DatabaseManager() {
    }

    private static EntityManager getEntityManager()
    {
        return emf.createEntityManager();
    }

    private static class EntityManagerHolder
    {
        private final static EntityManager INSTANCE = DatabaseManager.getEntityManager();
    }

    public static EntityManager getInstance()
    {
        return EntityManagerHolder.INSTANCE;
    }
}
