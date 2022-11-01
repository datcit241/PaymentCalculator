package com.example.paymentcalculator.data_storage;

import com.example.paymentcalculator.models.PaymentDetails;

public class PaymentDetailsRepository extends Repository<PaymentDetails> {
    private static PaymentDetailsRepository instance;
    private long countCustomers;
    private long countVips;
    private long totalRevenue;

    public static PaymentDetailsRepository getInstance() {
        if (instance == null) instance = new PaymentDetailsRepository();
        return instance;
    }

    private PaymentDetailsRepository() {
        super();
        this.countCustomers = 0;
        this.countVips = 0;
        this.totalRevenue = 0;
    }

    @Override
    public boolean insert(PaymentDetails paymentDetails) {
        boolean inserted = super.insert(paymentDetails);

        if (inserted) {
            this.countCustomers++;
            if (paymentDetails.isVip() && this.countVips++ > -1);
            this.totalRevenue += paymentDetails.getTotal();
        }

        return inserted;
    }

    @Override
    public boolean delete(PaymentDetails paymentDetails) {
        boolean deleted = super.delete(paymentDetails);

        if (deleted) {
            this.countCustomers--;
            if (paymentDetails.isVip() && this.countVips-- > -1);
            this.totalRevenue -= paymentDetails.getTotal();
        }

        return deleted;
    }

    public long getCountCustomers() {
        return countCustomers;
    }

    public long getCountVips() {
        return countVips;
    }

    public long getTotalRevenue() {
        return totalRevenue;
    }
}
