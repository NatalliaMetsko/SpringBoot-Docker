package com.netcracker.metsko.entity_manager;

public class EntityManager {

    private EntityManager() {
    }

    private static class EntityManagerHolder
    {
        private final static EntityManager INSTANCE = new EntityManager();
    }

    public  static EntityManager getInstance()
    {
        return  EntityManagerHolder.INSTANCE;
    }


}
