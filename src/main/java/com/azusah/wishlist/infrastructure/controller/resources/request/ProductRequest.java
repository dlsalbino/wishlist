package com.azusah.wishlist.infrastructure.controller.resources.request;

import jakarta.validation.constraints.NotBlank;

public record ProductRequest(
        @NotBlank(message = "'id' of product is required") String id,
        @NotBlank(message = "'name' of product is required") String name,
        @NotBlank(message = "'image' of product is required") String image,
        @NotBlank(message = "'value' of product is required") String value,
        @NotBlank(message = "'link' of product is required") String link
) {
}

