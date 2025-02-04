package com.nacu.sport.services;

import java.util.List;

public interface CrudService<T, ID>
{
    List<T> findAll();
    T findById(ID id);
    T create(T object);
    T update(T object);
    void deleteById(ID id);
}