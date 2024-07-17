package com.azusah.wishlist.infrastructure.controller.resources.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank(message = "'id' of product is required") String id,
        @NotBlank(message = "'name' of product is required") String name,
        @NotBlank(message = "'image' of product is required") String image,
        @Positive(message = "'value' of product is required and greater than zero") BigDecimal value,
        @NotBlank(message = "'link' of product is required") String link
) {
}

