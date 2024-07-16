package com.azusah.wishlist.infrastructure.controller.resources.response;

import java.util.HashSet;
import java.util.Set;

public class WishlistResponse {
    private String clientId;
    private Set<ProductResponse> products = new HashSet<>();

    public WishlistResponse(String clientId, Set<ProductResponse> products) {
        this.clientId = clientId;
        this.products = products;
    }

    public String getClientId() {
        return clientId;
    }

    public Set<ProductResponse> getProducts() {
        return products;
    }
}
