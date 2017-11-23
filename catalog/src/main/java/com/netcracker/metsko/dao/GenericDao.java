package com.netcracker.metsko.dao;

import java.sql.SQLException;

public interface GenericDao<T, Long> {

    void create(T newObject) throws SQLException;

    Object read(Long id) throws SQLException;

    Object update(T objectToUpdate)throws SQLException;

    void delete(Long id)throws SQLException;

}
