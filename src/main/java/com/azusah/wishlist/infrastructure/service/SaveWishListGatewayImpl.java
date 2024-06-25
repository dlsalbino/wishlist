package com.azusah.wishlist.infrastructure.service;

import com.azusah.wishlist.domain.entity.Wishlist;
import com.azusah.wishlist.gateway.SaveWishListGateway;
import org.springframework.stereotype.Service;

@Service
public class SaveWishListGatewayImpl implements SaveWishListGateway {
    @Override
    public Wishlist save(Wishlist wishlist) {
        return wishlist;
    }
}
