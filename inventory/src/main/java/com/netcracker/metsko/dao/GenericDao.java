package com.netcracker.metsko.dao;

public interface GenericDao<T, Long> {

    void create(T newObject);

    Object read(Long id);

    Object update(T objectToUpdate);

    void delete(T objectToDelete);

}

