package com.azusah.wishlist.infrastructure.controller.resources.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.HashSet;
import java.util.Set;

public class WishlistResponse {
    @Schema(example = "becfef1f-296c-4e6a-8609-200c9aa7f5cd", description = "The id of an existent customer.")
    private String customerId;
    private Set<ProductResponse> products = new HashSet<>();

    public WishlistResponse(String customerId, Set<ProductResponse> products) {
        this.customerId = customerId;
        this.products = products;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Set<ProductResponse> getProducts() {
        return products;
    }
}
