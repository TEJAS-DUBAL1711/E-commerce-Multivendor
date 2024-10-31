package com.TJ7.ecommerce.multivendor.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 *@EqualsAndHashCode:
 Without this, we will get stackOverflowError when we try to use wishlist in cartItem
 because cartItem has wishlist and wishlist has cartItem, hence we need to exclude
 cartItem from the equality check. */


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    private User user;

    @ManyToMany
    private Set<Product> products=new HashSet();



}
