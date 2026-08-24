package com.ecommerce.paymentservice.dtos;

import lombok.Data;

@Data
public class PaymentDto {
    private Long orderId;
    private String callbackUrl;
}
