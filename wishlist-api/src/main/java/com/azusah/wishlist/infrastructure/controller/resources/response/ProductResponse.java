package com.azusah.wishlist.infrastructure.controller.resources.response;

import java.math.BigDecimal;

public record ProductResponse(String id,
                              String name,
                              String image,
                              BigDecimal value,
                              String link) {
}
