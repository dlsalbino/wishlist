package com.azusah.wishlist.repository.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "wishlist")
public class WishlistEntity {

    @Id
    private String id;

    private String userId;

    private Set<ProductEntity> products;

    public WishlistEntity(String userId, Set<ProductEntity> products) {
        this.userId = userId;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public Set<ProductEntity> getProducts() {
        return products;
    }
}
