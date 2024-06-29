package com.azusah.wishlist.infrastructure.service;

import com.azusah.wishlist.domain.entity.Product;
import com.azusah.wishlist.gateway.RetrieveProductsGateway;
import com.azusah.wishlist.infrastructure.mapper.PersistenceEntityMapper;
import com.azusah.wishlist.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RetrieveProductsGatewayImpl implements RetrieveProductsGateway {

    private final WishlistRepository wishlistRepository;
    private final PersistenceEntityMapper mapper;

    @Autowired
    public RetrieveProductsGatewayImpl(WishlistRepository wishlistRepository, PersistenceEntityMapper mapper) {
        this.wishlistRepository = wishlistRepository;
        this.mapper = mapper;
    }

    @Override
    public Set<Product> findAllByUser(String userId) {
        return wishlistRepository.findByUserId(userId)
                .map(entity -> entity.getProducts()
                        .stream()
                        .map(mapper::fromEntity)
                        .collect(Collectors.toSet())
                )
                .orElseGet(Set::of);
    }
}
