package com.netcracker.metsko.dao;

import java.sql.SQLException;

public interface GenericDao<T, ID> {

    void create(T newObject) throws SQLException;

    Object read(ID id) throws SQLException;

    Object update(T objectToUpdate)throws SQLException;

    void delete(T objectToDelete)throws SQLException;

}
