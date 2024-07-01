package com.azusah.wishlist.repository.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "wishlist")
public class WishlistEntity {

    @Id
    private String id;

    private String clientId;

    private Set<ProductEntity> products;

    public WishlistEntity(String clientId, Set<ProductEntity> products) {
        this.clientId = clientId;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }

    public Set<ProductEntity> getProducts() {
        return products;
    }
}
