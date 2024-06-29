package com.azusah.wishlist.infrastructure.mapper;

import com.azusah.wishlist.domain.entity.Product;
import com.azusah.wishlist.domain.entity.Wishlist;
import com.azusah.wishlist.repository.entity.ProductEntity;
import com.azusah.wishlist.repository.entity.WishlistEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class PersistenceEntityMapper {

    public WishlistEntity toWishlistEntity(String userId, Product product) {
        return new WishlistEntity(userId,
                Collections.singleton(new ProductEntity(
                        product.getId(),
                        product.getName(),
                        product.getImage(),
                        product.getValue(),
                        product.getLink()))
        );
    }

    public ProductEntity from(Product product) {
        return new ProductEntity(
                product.getId(),
                product.getName(),
                product.getImage(),
                product.getValue(),
                product.getLink());
    }

    public Wishlist toWishlistModel(WishlistEntity entity) {
        return Wishlist.builder()
                .userId(entity.getUserId())
                .products(entity.getProducts().stream()
                        .map(productEntity -> Product.builder()
                                .id(productEntity.getId())
                                .name(productEntity.getName())
                                .image(productEntity.getImage())
                                .value(productEntity.getValue())
                                .link(productEntity.getLink())
                                .build())
                        .collect(Collectors.toSet()))
                .build();
    }
}
