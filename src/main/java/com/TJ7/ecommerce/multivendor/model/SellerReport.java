package com.TJ7.ecommerce.multivendor.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SellerReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    @OneToOne
    private Seller seller;

    private long totalEarnings = 0l;

    private long totalSales = 0l;

    private  long totalRefunds = 0l;

    private long totalTax = 0l;

    private Long netEarnings = 0L;

    private Integer totalOrders= 0;

    private Integer canceledOrders=0;

    private Integer totalTransactions=0;



}
