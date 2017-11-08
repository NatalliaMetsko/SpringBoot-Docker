package com.netcracker.metsko.entitymanager;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerFactory {

    public EntityManagerFactory() {
    }

    private static javax.persistence.EntityManagerFactory getEntityManagerFactory()
    {
        return Persistence.createEntityManagerFactory("InventoryPU");
    }
    
    private static class EntityManagerHolder
    {
        private final static EntityManager INSTANCE= getEntityManagerFactory().createEntityManager();
    }

    public static EntityManager getInstance()
    {
        return EntityManagerHolder.INSTANCE;
    }
    
}
