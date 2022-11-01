package com.example.paymentcalculator.services;

import com.example.paymentcalculator.utils.Validator;

import java.util.*;

public class PaymentServices {
    private static final int COST = 20_000;
    private static final Validator<String> namePatternValidator = new Validator<>(name -> name.matches("^[a-zA-Z\\s]{1,20}"), "Invalid name");
    private static final Validator<String> nullOrEmptyValidator = new Validator<>(str -> str != null && !str.isEmpty());
    private static final Validator<String> amountFormatValidator = new Validator<>(amount -> {
        try {
            Long.parseLong(amount);
        } catch (Exception exception) {
            return false;
        }

        return true;
    }, "Invalid number format for amount");

    public static long calculate(long amount, boolean vip) {
        long total = amount * COST;
        if (vip) {
            total = total * 90 / 100;
        }
        return total;
    }

    public static List<String> validate(String name, String amount) {
        List<String> results = new ArrayList<>();

        String message = nullOrEmptyValidator.validate(name, "Name should not be empty");
        addResult(results, message);

        message = nullOrEmptyValidator.validate(amount, "Amount should not be empty");
        addResult(results, message);

        if (results.isEmpty()) {
            message = namePatternValidator.validate(name);
            addResult(results, message);

            message = amountFormatValidator.validate(amount);
            addResult(results, message);
        }

        return results;
    }

    private static void addResult(List<String> list, String message) {
        if (message != null) {
            list.add(message);
        }
    }

}
