package com.ecommerce.paymentservice.controllers;

import com.ecommerce.paymentservice.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @PostMapping("/payments")
    public String initiatePayment(){
        return paymentService.initiatePayment();
    }
}
