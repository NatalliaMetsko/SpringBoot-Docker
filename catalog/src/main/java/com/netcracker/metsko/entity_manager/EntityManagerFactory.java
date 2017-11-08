package com.netcracker.metsko.entity_manager;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerFactory {

    public EntityManagerFactory() {
    }

    private static javax.persistence.EntityManagerFactory getEntityManagerFactory()
    {
        return Persistence.createEntityManagerFactory("CatalogPU");
    }
    
    private static  class EntityManagerHolder
    {
        
        private final static EntityManager INSTANCE= getEntityManagerFactory().createEntityManager();
    }

    public EntityManager getInstance()
    {
        return EntityManagerHolder.INSTANCE;
    }
    
}
