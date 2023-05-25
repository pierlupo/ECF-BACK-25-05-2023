package org.example._interface;

import java.util.List;

public interface Repository<T> {

    boolean create(T element);

    boolean update(T element);

    boolean delete(T element);

    T findById(int id);

    List<T> findAll();
}