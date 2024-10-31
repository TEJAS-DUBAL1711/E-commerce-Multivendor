package com.TJ7.ecommerce.multivendor.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    private String description;

    private int mrpPrice;

    private int sellingPrice;

    private int discountPercentage;

    private int quantity;

    private String color;

    /**
     * This class represents a product in the e-commerce application.
     * Each product has a unique id, title, description, mrpPrice, sellingPrice, discountPercentage, quantity, color and images.
     * The id is generated automatically by the database. The title is the name of the product.
     * The description is a brief summary of the product. The mrpPrice is the price of the product
     * at which it is sold by the manufacturer. The sellingPrice is the price at which the product
     * is sold by the vendor. The discountPercentage is the discount offered by the vendor on the
     * mrpPrice. The quantity is the number of items of the product available for sale. The color is
     * the color of the product. The images is a list of urls of images of the product.
     * The product also has a category, seller, createdAt and numRatings.
     * The category is the category in which the product belongs. The seller is the seller of the product.
     * The createdAt is the time at which the product was created. The numRatings is the number of ratings
     * the product has received.
     */


    @ElementCollection
    private List<String> images = new ArrayList<>();


    private int numRatings;

    @ManyToOne
    private  Category category;


    @ManyToOne
    private Seller seller;

    private LocalDateTime createdAt;

  //  @ElementCollection
    private String sizes;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    private  boolean in_stock = true;
}
