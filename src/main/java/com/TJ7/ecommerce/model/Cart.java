package com.TJ7.ecommerce.model;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    /*
    // @OneToMany annotation is used to define a one-to-many relationship between two tables in database.
    // In this case, a Cart can have multiple CartItems.
    // mappedBy = "cart" means that the relationship is mapped by the "cart" field in the CartItem class.
    // cascade = CascadeType.ALL means that all operations (e.g. persist, merge, delete) will be cascaded to the target of the association.
    // orphanRemoval = true means that if a CartItem is removed from a Cart, it will be automatically deleted from the database.
    // fetch = FetchType.LAZY means that the cartItems will be loaded only when they are actually needed. This can improve performance by avoiding unnecessary database queries.
    @OneToMany annotation is used to define a one-to-many relationship between two tables in database.
    // In this case, a Cart can have multiple CartItems.
    // mappedBy = "cart" means that the relationship is mapped by the "cart" field in the CartItem class.
    // cascade = CascadeType.ALL means that all operations (e.g. persist, merge, delete) will be cascaded to the target of the association.
    // orphanRemoval = true means that if a CartItem is removed from a Cart, it will be automatically deleted from the database.  */

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CartItem> cartItems = new HashSet<>();

    private double totalSellingPrice;

    private int totalItem;

    private int totalMrpPrice;

    private int discount;

    private String couponCode;

    private int couponPrice;
}
