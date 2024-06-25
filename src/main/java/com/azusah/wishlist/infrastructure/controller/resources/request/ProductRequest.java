package com.azusah.wishlist.infrastructure.controller.resources.request;

public record ProductRequest(
        String id,
        String name,
        String image,
        String value,
        String link
) {
}

