package com.azusah.wishlist.infrastructure.controller.resources.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record ProductResponse(
        @Schema(example = "6c3af85d-53ca-4cfe-8f91-ab6e1e727450", description = "The id of an existent product.")
        String id,
        @Schema(example = "Fundamentals of Software Architecture", description = "The name of an existent product.")
        String name,
        @Schema(example = "https://eccomerce.com/images/fundamentals-book.png", description = "The product's image url.")
        String image,
        @Schema(example = "99.90", description = "The currency value of the product.")
        BigDecimal value,
        @Schema(example = "https://eccomerce.com/products/6c3af85d-53ca-4cfe-8f91-ab6e1e727450", description = "The product's url.")
        String link) {
}
