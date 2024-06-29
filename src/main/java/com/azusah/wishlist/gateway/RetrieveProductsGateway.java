package com.azusah.wishlist.gateway;

import com.azusah.wishlist.domain.entity.Product;

import java.util.Set;

public interface RetrieveProductsGateway {

    Set<Product> findAllByUser(String userId);
}
