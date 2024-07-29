package com.azusah.wishlist.infrastructure.controller.resources.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        @Schema(example = "6c3af85d-53ca-4cfe-8f91-ab6e1e727450", description = "The id of an existent product.")
        @NotBlank(message = "'id' of product is required")
        String id,
        @Schema(example = "Fundamentals of Software Architecture", description = "The name of an existent product.")
        @NotBlank(message = "'name' of product is required")
        String name,
        @Schema(example = "https://eccomerce.com/images/fundamentals-book.png", description = "The product's image url.")
        @NotBlank(message = "'image' of product is required")
        String image,
        @Schema(example = "99.90", description = "The currency value of the product.")
        @Positive(message = "'value' of product is required and greater than zero")
        BigDecimal value,
        @Schema(example = "https://eccomerce.com/products/6c3af85d-53ca-4cfe-8f91-ab6e1e727450", description = "The product's url.")
        @NotBlank(message = "'link' of product is required")
        String link
) {
}

