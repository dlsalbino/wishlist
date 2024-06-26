package com.azusah.wishlist.infrastructure.controller.resources.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequest(
        @NotNull @NotBlank(message = "Um 'id' deve ser enviado!") String id,
        @NotNull String name,
        @NotNull String image,
        @NotNull String value,
        @NotNull String link
) {
}

