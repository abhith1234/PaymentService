package com.ecommerce.paymentservice.services;

import com.ecommerce.paymentservice.dtos.PaymentVarificationResponseDto;
import org.springframework.web.bind.annotation.RequestParam;

public interface PaymentService {
    String initiatePayment(long orderId, String callbackUrl);
    PaymentVarificationResponseDto verifyCheckoutSession(String sessionId);
}
