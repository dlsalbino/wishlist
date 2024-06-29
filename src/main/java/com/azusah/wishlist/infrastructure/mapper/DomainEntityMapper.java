package com.azusah.wishlist.infrastructure.mapper;

import com.azusah.wishlist.domain.entity.Product;
import com.azusah.wishlist.domain.entity.Wishlist;
import com.azusah.wishlist.infrastructure.controller.resources.request.AddProductRequest;
import com.azusah.wishlist.infrastructure.controller.resources.response.AddProductResponse;
import com.azusah.wishlist.infrastructure.controller.resources.response.ProductResponse;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class DomainEntityMapper {

    public Product toProduct(AddProductRequest request) {
        return Product.builder()
                .id(request.getProduct().id())
                .name(request.getProduct().name())
                .value(request.getProduct().value())
                .image(request.getProduct().image())
                .link(request.getProduct().link())
                .build();
    }

    public AddProductResponse toResponse(Wishlist wishlist) {
        return new AddProductResponse(wishlist.getUserId(),
                wishlist.getProducts().stream().map((item) -> {
                    return new ProductResponse(
                            item.getId(), item.getName(), item.getImage(), item.getValue(), item.getLink()
                    );
                }).collect(Collectors.toSet()));
    }
}
