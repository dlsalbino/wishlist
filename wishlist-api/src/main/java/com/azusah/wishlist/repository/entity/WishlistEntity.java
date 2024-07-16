package com.azusah.wishlist.repository.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "wishlist")
public class WishlistEntity {

    @Id
    private String id;

    private String customerId;

    private Set<ProductEntity> products;

    public WishlistEntity(String customerId, Set<ProductEntity> products) {
        this.customerId = customerId;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Set<ProductEntity> getProducts() {
        return products;
    }
}
