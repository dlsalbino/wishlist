package com.azusah.wishlist.infrastructure.controller.resources.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddProductRequest {

    @NotBlank(message = "'clientId' is required")
    private String clientId;

    @Valid
    @NotNull(message = "'product' is required")
    private ProductRequest product;

    public AddProductRequest() {
    }

    public String getClientId() {
        return clientId;
    }

    public ProductRequest getProduct() {
        return product;
    }
}
