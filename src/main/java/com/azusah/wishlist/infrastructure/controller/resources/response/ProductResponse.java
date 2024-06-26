package com.azusah.wishlist.infrastructure.controller.resources.response;

public record ProductResponse(String id,
                              String name,
                              String image,
                              String value,
                              String link) {
}
