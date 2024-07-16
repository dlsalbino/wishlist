package com.azusah.wishlist.infrastructure.mapper;

import com.azusah.wishlist.core.domain.entity.Product;
import com.azusah.wishlist.core.domain.entity.Wishlist;
import com.azusah.wishlist.infrastructure.controller.resources.request.ProductRequest;
import com.azusah.wishlist.infrastructure.controller.resources.response.ProductResponse;
import com.azusah.wishlist.infrastructure.controller.resources.response.WishlistResponse;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DomainEntityMapper {

    public Product toProduct(ProductRequest request) {
        return Product.builder()
                .id(request.id())
                .name(request.name())
                .value(request.value())
                .image(request.image())
                .link(request.link())
                .build();
    }

    public WishlistResponse toResponse(Wishlist wishlist) {
        return new WishlistResponse(wishlist.getClientId(),
                wishlist.getProducts().stream().map((item) -> {
                    return new ProductResponse(
                            item.getId(), item.getName(), item.getImage(), item.getValue(), item.getLink()
                    );
                }).collect(Collectors.toSet()));
    }

    public Set<ProductResponse> toProductResponse(Set<Product> products) {
        return products
                .stream()
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getImage(),
                        product.getValue(),
                        product.getLink()))
                .collect(Collectors.toSet());
    }
}
