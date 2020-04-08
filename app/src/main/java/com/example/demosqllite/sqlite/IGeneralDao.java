package com.example.demosqllite.sqlite;

import java.sql.SQLException;
import java.util.List;

public interface IGeneralDao<T> {
    T insert(T t) throws SQLException;

    T findById(int id);

    List<T> findAll();

    boolean removeById(int id) throws SQLException;

    boolean updateById(T t) throws SQLException;
}
