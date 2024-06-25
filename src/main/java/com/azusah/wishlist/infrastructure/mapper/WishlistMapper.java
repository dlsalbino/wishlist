package com.azusah.wishlist.infrastructure.mapper;

import com.azusah.wishlist.domain.entity.Product;
import com.azusah.wishlist.domain.entity.User;
import com.azusah.wishlist.domain.entity.Wishlist;
import com.azusah.wishlist.infrastructure.controller.resources.request.AddProductRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class WishlistMapper {

    public Wishlist toWishlist(AddProductRequest request) {
        return Wishlist.builder()
                .id(UUID.randomUUID().toString())
                .user(User.builder()
                        .id(request.getUser().id())
                        .logged(request.getUser().logged())
                        .build())
                .products(request.getProducts()
                        .stream()
                        .map(productRequest -> Product.builder()
                                .id(productRequest.id())
                                .name(productRequest.name())
                                .image(productRequest.image())
                                .value(productRequest.value())
                                .link(productRequest.link())
                                .build())
                        .collect(Collectors.toSet()))
                .build();
    }
}
