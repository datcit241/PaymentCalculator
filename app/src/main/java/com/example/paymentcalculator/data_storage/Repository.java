package com.example.paymentcalculator.data_storage;

import java.util.Collection;
import java.util.HashSet;

public class Repository<T> implements IRepository<T> {
    private Collection<T> entityList;

    public Repository() {
        this.entityList = new HashSet<>();
    }

    @Override
    public Iterable<T> get() {
        return this.entityList;
    }

    @Override
    public boolean insert(T t) {
        return this.entityList.add(t);
    }

    @Override
    public boolean delete(T t) {
        return this.entityList.remove(t);
    }
}
