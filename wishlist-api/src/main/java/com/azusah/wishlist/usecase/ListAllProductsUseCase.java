package com.azusah.wishlist.usecase;

import com.azusah.wishlist.core.domain.entity.Product;

import java.util.Set;

public interface ListAllProductsUseCase {

    Set<Product> execute(String userId);
}
