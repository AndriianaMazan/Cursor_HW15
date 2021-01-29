package com.cursor.Dao.interfaces;

import java.util.List;

public interface CRUD<T> {
    boolean create(T model);

    T read(Integer id);

    void update(T model);

    boolean delete(T model);

    List<T> getAll(Class<T> type);
}
