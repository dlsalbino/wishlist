package com.azusah.wishlist.infrastructure.service;

import com.azusah.wishlist.domain.entity.Product;
import com.azusah.wishlist.domain.entity.Wishlist;
import com.azusah.wishlist.domain.exception.WishlistUpdateException;
import com.azusah.wishlist.gateway.PersistenceGateway;
import com.azusah.wishlist.infrastructure.mapper.PersistenceEntityMapper;
import com.azusah.wishlist.repository.WishlistRepository;
import com.azusah.wishlist.repository.entity.WishlistEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PersistenceGatewayImpl implements PersistenceGateway {

    private static final Logger log = LoggerFactory.getLogger(PersistenceGatewayImpl.class);
    private final WishlistRepository repository;
    private final PersistenceEntityMapper mapper;

    @Autowired
    public PersistenceGatewayImpl(WishlistRepository repository, PersistenceEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Wishlist save(String userId, Product product) {
        WishlistEntity savedWishlist = repository.save(mapper.toWishlistEntity(userId, product));
        log.info("Wishlist '{}' created to client '{}'", savedWishlist.getId(), savedWishlist.getUserId());
        return mapper.toWishlist(savedWishlist);
    }

    @Override
    public Wishlist addProduct(String userId, Product product) {
        try {
            Optional<WishlistEntity> optionalWishlistEntity = repository.findByUserId(userId);
            if (optionalWishlistEntity.isPresent()) {
                WishlistEntity wishlistEntity = optionalWishlistEntity.get();
                boolean alreadyAdded = wishlistEntity.getProducts()
                        .stream()
                        .anyMatch(existentProduct -> existentProduct.getId().equals(product.getId()));
                if (alreadyAdded) {
                    log.warn("Product '{}' already added in wishlist from client '{}'", product.getId(), userId);
                    return mapper.toWishlist(optionalWishlistEntity.get());
                } else {
                    wishlistEntity.getProducts().add(mapper.toProductEntity(product));
                    WishlistEntity saved = repository.save(wishlistEntity);
                    log.info("Wishlist '{}' from client '{}' was updated", saved.getId(), saved.getUserId());
                    return mapper.toWishlist(saved);
                }
            }
        } catch (Exception e) {
            String message = "Something was wrong while adding product "
                    + product.getId() + " for client '" + userId + "'.";
            log.error("ERROR: {}", message);
            throw new WishlistUpdateException(message, e);
        }
        return null;
    }

    @Override
    public Set<Product> findAllProductsByUser(String userId) {
        return repository.findByUserId(userId)
                .map(entity -> entity.getProducts()
                        .stream()
                        .map(mapper::toProduct)
                        .collect(Collectors.toSet())
                )
                .orElseGet(Set::of);
    }

    @Override
    public Optional<Wishlist> findWishlistByUser(String userId) {
        return repository.findByUserId(userId).map(mapper::toWishlist);
    }
}
