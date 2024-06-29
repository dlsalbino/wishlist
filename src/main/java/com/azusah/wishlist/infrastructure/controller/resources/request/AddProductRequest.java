package com.azusah.wishlist.infrastructure.controller.resources.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddProductRequest {

    @NotBlank(message = "'userId' is required")
    private String userId;

    @Valid
    @NotNull(message = "'product' is required")
    private ProductRequest product;

    public AddProductRequest() {
    }

    public String getUserId() {
        return userId;
    }

    public ProductRequest getProduct() {
        return product;
    }
}
