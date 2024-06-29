package com.azusah.wishlist.infrastructure.service;

import com.azusah.wishlist.domain.entity.Wishlist;
import com.azusah.wishlist.gateway.RetrieveWishlistGateway;
import com.azusah.wishlist.infrastructure.mapper.PersistenceEntityMapper;
import com.azusah.wishlist.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RetrieveWishlistGatewayImpl implements RetrieveWishlistGateway {

    private final WishlistRepository repository;
    private final PersistenceEntityMapper mapper;

    @Autowired
    public RetrieveWishlistGatewayImpl(WishlistRepository repository, PersistenceEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Wishlist> findWishlistByUser(String userId) {
        return repository.findByUserId(userId).map(mapper::toWishlistModel);
    }

}
