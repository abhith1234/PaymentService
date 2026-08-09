package com.ecommerce.paymentservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentGatewaySelectorImpl implements PaymentGatewaySelector{
    private RazorpayGateway razorpayGateway;
    private StripeGateway stripeGateway;
    public PaymentGatewaySelectorImpl(RazorpayGateway razorpayGateway, StripeGateway stripeGateway){
        this.razorpayGateway = razorpayGateway;
        this.stripeGateway = stripeGateway;
    }
    @Override
    public PaymentGateway get() {
        return stripeGateway;
    }
}
