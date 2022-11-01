package com.example.paymentcalculator.data_storage;

public interface IRepository<T> {
    Iterable<T> get();
    boolean insert(T t);
    boolean delete(T t);
}
