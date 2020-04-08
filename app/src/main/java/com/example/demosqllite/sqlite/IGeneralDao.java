package com.example.demosqllite.sqlite;

import java.sql.SQLException;
import java.util.List;

public interface IGeneralDao<T> {
    T insert(T t);

    T findById(int id);

    List<T> findAll();

    boolean removeById(int id);

    boolean updateById(T t);
}
