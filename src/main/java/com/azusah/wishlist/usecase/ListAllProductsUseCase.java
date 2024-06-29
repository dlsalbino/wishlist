package com.azusah.wishlist.usecase;

import com.azusah.wishlist.domain.entity.Product;

import java.util.Set;

public interface ListAllProductsUseCase {

    Set<Product> execute(String userId);
}
