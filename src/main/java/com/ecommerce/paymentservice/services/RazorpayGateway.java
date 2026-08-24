package com.ecommerce.paymentservice.services;

import org.springframework.stereotype.Service;

@Service
public class RazorpayGateway implements PaymentGateway{
    @Override
    public String generatePaymentLink(long amount, String callbackUrl, long orderId) {
        return "";
    }
}
