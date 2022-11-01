package com.example.paymentcalculator.utils;

public class Validator<T> {
    private String defaultMessage;
    private Predicate<T> predicate;

    public Validator(Predicate<T> predicate) {
        this.predicate = predicate;
    }

    public Validator(Predicate<T> predicate ,String defaultMessage) {
        this.predicate = predicate;
        this.defaultMessage = defaultMessage;
    }

    public String validate(T t) {
        if (!predicate.test(t)) {
            return defaultMessage;
        }

        return null;
    }

    public String validate(T t, String message) {
        if (!predicate.test(t)) {
            return message;
        }

        return null;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public Predicate<T> getPredicate() {
        return predicate;
    }

    public void setPredicate(Predicate<T> predicate) {
        this.predicate = predicate;
    }

}
