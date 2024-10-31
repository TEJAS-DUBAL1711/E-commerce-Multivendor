package com.TJ7.ecommerce.model;

import com.TJ7.ecommerce.multivendor.domain.PaymentMethod;
import com.TJ7.ecommerce.multivendor.domain.PaymentOrderStatus;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

public class PaymentOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long amount;

    private PaymentOrderStatus status = PaymentOrderStatus.PENDING;

    private PaymentMethod paymentMethod;

    private String paymentLinkId;


    @ManyToOne
    private User user;

    @OneToMany
    private Set<Order> orders = new HashSet<>();

}
