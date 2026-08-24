package com.ecommerce.paymentservice.controllers;

import com.ecommerce.paymentservice.dtos.PaymentDto;
import com.ecommerce.paymentservice.dtos.PaymentVarificationResponseDto;
import com.ecommerce.paymentservice.services.PaymentService;
import com.stripe.Stripe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @PostMapping("/payments")
    public String initiatePayment(@RequestBody PaymentDto paymentDto){
        return paymentService.initiatePayment(paymentDto.getOrderId(), paymentDto.getCallbackUrl());
    }

    @GetMapping("/payments/verify")
    public ResponseEntity<PaymentVarificationResponseDto> verifyCheckoutSession(@RequestParam("session_id") String sessionId) {
        return new ResponseEntity<>(paymentService.verifyCheckoutSession(sessionId), HttpStatus.OK);
    }
}
