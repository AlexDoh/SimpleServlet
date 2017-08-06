package com.odmytrenko.dao;

import java.sql.Connection;

public abstract class AbstractDao<T> implements GenericDao<T> {

    protected final Connection connection;

    public AbstractDao(Connection connection) {
        this.connection = connection;
    }
}
