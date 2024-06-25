package com.azusah.wishlist.gateway;

import com.azusah.wishlist.domain.entity.Wishlist;

import java.util.Optional;

public interface RetrieveWishListGateway {
    Optional<Wishlist> findWishlistByUser(String id);
}
