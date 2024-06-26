package com.azusah.wishlist.infrastructure.mapper;

import com.azusah.wishlist.domain.entity.Product;
import com.azusah.wishlist.infrastructure.controller.resources.request.AddProductRequest;
import org.springframework.stereotype.Component;

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
}
