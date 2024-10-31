package com.TJ7.ecommerce.model;

import com.TJ7.ecommerce.multivendor.domain.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetails {

    private String paymentId;
    private String razorpayPaymentLinkId;
    private String razorpayPaymentLinkedReferenceId;
    private String getRazorpayPaymentLinkStatus;
    private String razorpayPaymentId;
    private PaymentStatus status;
}
