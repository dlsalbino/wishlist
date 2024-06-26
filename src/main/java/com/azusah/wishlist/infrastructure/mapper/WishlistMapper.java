package com.azusah.wishlist.infrastructure.mapper;

import com.azusah.wishlist.domain.entity.Product;
import com.azusah.wishlist.domain.entity.Wishlist;
import com.azusah.wishlist.infrastructure.controller.resources.request.AddProductRequest;
import org.springframework.stereotype.Component;

@Component
public class WishlistMapper {

    public Wishlist toWishlist(AddProductRequest request) {
        return new Wishlist(
                request.getUserId(),
                Product.builder()
                        .id(request.getProduct().id())
                        .name(request.getProduct().name())
                        .value(request.getProduct().value())
                        .image(request.getProduct().image())
                        .link(request.getProduct().link())
                        .build());
    }
}
