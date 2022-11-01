package com.example.paymentcalculator.models;

import com.example.paymentcalculator.utils.CurrencyFormat;

public class PaymentDetails {
    private String name;
    private long amount;
    private boolean vip;
    private long total;

    public PaymentDetails(String name, long amount, long total, boolean vip) {
        this.name = name;
        this.amount = amount;
        this.total = total;
        this.vip = vip;
    }

    public String getName() {
        return name;
    }

    public long getAmount() {
        return amount;
    }

    public long getTotal() {
        return total;
    }

    public boolean isVip() {
        return vip;
    }

    @Override
    public String toString() {
        return
                this.name
                        + "     " + this.amount
                        + "     " + CurrencyFormat.format(total)
                        + (this.vip ? "     VIP" : "")
                ;
    }
}
