package com.azusah.wishlist.infrastructure.service;

import com.azusah.wishlist.domain.entity.Product;
import com.azusah.wishlist.domain.entity.Wishlist;
import com.azusah.wishlist.gateway.RetrieveWishlistGateway;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

@Service
public class RetrieveWishlistGatewayImpl implements RetrieveWishlistGateway {
    @Override
    public Optional<Wishlist> findWishlistByUser(String id) {
        if (Objects.equals(id, "userId123")) {
            return Optional.of(Wishlist.builder()
                    .userId(id)
                    .products(getProductListWith(10))
                    .build());
        } else if (Objects.equals(id, "userId123Full")) {
            return Optional.of(Wishlist.builder()
                    .userId(id)
                    .products(getProductListWith(20))
                    .build());
        }
        return Optional.empty();
    }

    private HashSet<Product> getProductListWith(Integer products) {
        HashSet<Product> productsList = new HashSet<>();
        for (int i = 0; i < products; i++) {
            productsList.add(Product.builder()
                    .id(RandomStringUtils.randomAlphanumeric(10))
                    .name(RandomStringUtils.randomAlphanumeric(20))
                    .image(RandomStringUtils.randomAlphanumeric(30))
                    .value(RandomStringUtils.randomNumeric(3, 5))
                    .link(RandomStringUtils.randomAlphanumeric(50))
                    .build());
        }
        return productsList;
    }
}
