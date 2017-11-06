package com.netcracker.metsko.DAO;

import java.util.*;

public interface GenericDAO<Object, Long> {

    void add(Object object);

    void remove(Long id);

    Object update(Long id);

    List<Object> findAll();
}
