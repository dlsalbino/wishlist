package com.azusah.wishlist.infrastructure.controller.resources.request;

import jakarta.validation.constraints.NotNull;

public class AddProductRequest {

    @NotNull
    private String userId;

    @NotNull(message = "Produto é obrigatório!")
    private ProductRequest product;

    public AddProductRequest() {
    }

    public @NotNull String getUserId() {
        return userId;
    }

    public ProductRequest getProduct() {
        return product;
    }
}
