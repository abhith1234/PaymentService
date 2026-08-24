package com.ecommerce.paymentservice.services;

import com.ecommerce.paymentservice.dtos.PaymentVarificationResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.stripe.model.checkout.Session;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentGatewaySelector paymentGatewaySelector;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String initiatePayment(long orderId, String callbackUrl) {
        Map<String, Object> response = restTemplate.getForObject("http://localhost:8080/orders/"+orderId, Map.class);
        long amount = 0;
        if (response != null) {
            amount = (long) ((Double)response.get("totalAmount")).doubleValue() * 100;
        }
        return paymentGatewaySelector
                .get().generatePaymentLink(amount, callbackUrl, orderId);
    }

    @Override
    public PaymentVarificationResponseDto verifyCheckoutSession(String sessionId) {
        try {
            Session session = Session.retrieve(sessionId);
PaymentVarificationResponseDto paymentVarificationResponseDto = new PaymentVarificationResponseDto();
            if ("paid".equals(session.getPaymentStatus())) {

                // 1. Extract your custom metadata map from the session object
                java.util.Map<String, String> metadata = session.getMetadata();

                // 2. Fetch your orderId using the key you defined earlier
                String orderId = metadata != null ? metadata.get("orderId") : null;

                String transactionId = session.getPaymentIntent();

                // 3. You now have both IDs to update your database cleanly!
                System.out.println("Updating Order: " + orderId + " with Transaction: " + transactionId);
                Map<String, Object> response = restTemplate.getForObject("http://localhost:8080/orders/" + orderId + "/confirm-payment?transaction_id=" + transactionId, Map.class);

                // orderRepository.markAsPaid(orderId, transactionId);
                paymentVarificationResponseDto.setSuccess(true);
                paymentVarificationResponseDto.setOrderId(Long.parseLong(orderId));
                return paymentVarificationResponseDto;
            } else {
                paymentVarificationResponseDto.setSuccess(false);
                return paymentVarificationResponseDto;
            }
        } catch (Exception e) {

            throw new RuntimeException(e.getMessage());
        }
    }
}
