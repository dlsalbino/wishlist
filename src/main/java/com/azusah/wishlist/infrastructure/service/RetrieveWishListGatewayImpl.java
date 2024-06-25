package com.azusah.wishlist.infrastructure.service;

import com.azusah.wishlist.domain.entity.User;
import com.azusah.wishlist.domain.entity.Wishlist;
import com.azusah.wishlist.gateway.RetrieveWishListGateway;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class RetrieveWishListGatewayImpl implements RetrieveWishListGateway {
    @Override
    public Optional<Wishlist> findWishlistByUser(String id) {
        if (id == "xyz") {
            return Optional.of(new Wishlist(User.builder().id("xyz").build(), new HashSet<>()));
        }
        return Optional.empty();
    }
}
