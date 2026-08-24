package com.ecommerce.paymentservice.dtos;

import lombok.Data;

@Data
public class PaymentVarificationResponseDto {
    boolean success;
    long orderId;
}
