package com.ecommerce.paymentservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentGatewaySelector paymentGatewaySelector;

    @Override
    public String initiatePayment() {
        return paymentGatewaySelector
                .get().generatePaymentLink();
    }
}
