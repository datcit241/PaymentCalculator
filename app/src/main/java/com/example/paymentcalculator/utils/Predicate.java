package com.example.paymentcalculator.utils;

@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}
