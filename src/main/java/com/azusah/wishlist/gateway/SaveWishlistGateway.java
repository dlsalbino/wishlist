package com.azusah.wishlist.gateway;

import com.azusah.wishlist.domain.entity.Product;
import com.azusah.wishlist.domain.entity.Wishlist;

public interface SaveWishlistGateway {

    Wishlist save(String userId, Product product);

    Wishlist addProduct(String userId, Product product);
}

