package com.azusah.wishlist.gateway;

import com.azusah.wishlist.domain.entity.Product;
import com.azusah.wishlist.domain.entity.Wishlist;

import java.util.Optional;
import java.util.Set;

public interface PersistenceGateway {

    Wishlist save(String userId, Product product);

    Wishlist addProduct(String userId, Product product);

    Wishlist removeProduct(String userId, Product product);

    Set<Product> findAllProductsByUser(String userId);

    Optional<Wishlist> findWishlistByUser(String id);
}
