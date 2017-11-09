package com.netcracker.metsko.databasemanager;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DatabaseManager {

    public DatabaseManager() {
    }

    private static EntityManager getEntityManager()
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CatalogPU");
        return emf.createEntityManager();
    }

    private static class EntityManagerHolder
    {
        private static final EntityManager INSTANCE = DatabaseManager.getEntityManager();
    }

    public static EntityManager getInstance()
    {
        return EntityManagerHolder.INSTANCE;
    }
}
