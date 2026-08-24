package com.ecommerce.paymentservice.services;

public interface PaymentGateway {
    String generatePaymentLink(long amount, String callbackUrl , long orderId);
}
