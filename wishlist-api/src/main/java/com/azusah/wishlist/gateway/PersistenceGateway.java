package com.azusah.wishlist.gateway;

import com.azusah.wishlist.core.domain.entity.Product;
import com.azusah.wishlist.core.domain.entity.Wishlist;

import java.util.Optional;
import java.util.Set;

public interface PersistenceGateway {

    Wishlist save(String clientId, Product product);

    Wishlist addProduct(String clientId, Product product);

    Wishlist removeProduct(String clientId, Product product);

    Set<Product> findAllProductsByClient(String clientId);

    Optional<Wishlist> findWishlistByCustomer(String customerId);
}
