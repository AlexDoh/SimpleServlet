package com.odmytrenko.dao;

public interface GenericDao<T> {

    T create(T t);

    T delete(T t);

    T update(T t);

    T findById(Long id);
}
