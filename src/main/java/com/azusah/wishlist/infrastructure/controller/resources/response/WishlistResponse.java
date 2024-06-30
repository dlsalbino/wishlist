package com.azusah.wishlist.infrastructure.controller.resources.response;

import java.util.HashSet;
import java.util.Set;

public class WishlistResponse {
    private String userId;
    private Set<ProductResponse> products = new HashSet<>();

    public WishlistResponse(String userId, Set<ProductResponse> products) {
        this.userId = userId;
        this.products = products;
    }

    public String getUserId() {
        return userId;
    }

    public Set<ProductResponse> getProducts() {
        return products;
    }
}
