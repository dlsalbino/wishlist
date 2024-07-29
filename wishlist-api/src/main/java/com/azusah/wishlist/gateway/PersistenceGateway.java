package com.azusah.wishlist.gateway;

import com.azusah.wishlist.core.domain.entity.Product;
import com.azusah.wishlist.core.domain.entity.Wishlist;

import java.util.Optional;
import java.util.Set;

public interface PersistenceGateway {

    Wishlist save(String customerId, Product product);

    Wishlist addProduct(String customerId, Product product);

    Wishlist removeProduct(String customerId, String productId);

    Set<Product> findAllProductsByCustomer(String customerId);

    Optional<Wishlist> findWishlistByCustomer(String customerId);
}
