package com.odmytrenko.service;

public interface CrudService<T> {

    T create(T user);

    T delete(T user);

    T update(T user);
}
