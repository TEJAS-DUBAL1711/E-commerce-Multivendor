package com.TJ7.ecommerce.multivendor.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

/*
 * The `@JsonIgnore` annotation is used to indicate that a particular field should be ignored when serializing or deserializing JSON data.
 * In this context, it prevents the `cart` field in the `CartItem` class from being included in the JSON representation of a `CartItem` object.
 * This can be useful to avoid circular references or reduce the amount of data sent over the network.
 *
 * The `@ManyToOne` annotation is used to define a many-to-one relationship between two entities in a JPA (Java Persistence API) context.
 *
 * - `@ManyToOne private Cart cart;`: This indicates that each `CartItem` is associated with one `Cart`.
 *   Multiple `CartItem` instances can be linked to a single `Cart`, establishing a many-to-one relationship.
 *
 * - `@ManyToOne private Product product;`: This indicates that each `CartItem` is associated with one `Product`.
 *   Multiple `CartItem` instances can be linked to a single `Product`, establishing a many-to-one relationship.
 */
    @JsonIgnore
    @ManyToOne
    private Cart cart;

    @ManyToOne
    private Product product;

    private String size;

    private int quantity;

    private int mrpPrice;

    private int sellingPrice;

    private long userId;





}
