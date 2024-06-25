package com.azusah.wishlist.infrastructure.service;

import com.azusah.wishlist.domain.entity.Wishlist;
import com.azusah.wishlist.gateway.SaveWishlistGateway;
import org.springframework.stereotype.Service;

@Service
public class SaveWishlistGatewayImpl implements SaveWishlistGateway {
    @Override
    public Wishlist save(Wishlist wishlist) {
        return wishlist;
    }
}
