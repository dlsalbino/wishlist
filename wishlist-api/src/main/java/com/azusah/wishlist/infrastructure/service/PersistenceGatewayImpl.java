package com.azusah.wishlist.infrastructure.service;

import com.azusah.wishlist.core.domain.entity.Product;
import com.azusah.wishlist.core.domain.entity.Wishlist;
import com.azusah.wishlist.core.domain.exception.WishlistNotFoundException;
import com.azusah.wishlist.core.domain.exception.WishlistUpdateException;
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
    public Wishlist save(String customerId, Product product) {
        WishlistEntity savedWishlist = repository.save(mapper.toWishlistEntity(customerId, product));
        log.info("Wishlist '{}' created to client '{}'", savedWishlist.getId(), savedWishlist.getCustomerId());
        return mapper.toWishlist(savedWishlist);
    }

    @Override
    public Wishlist removeProduct(String customerId, String productId) {
        Optional<WishlistEntity> optionalWishlistEntity = repository.findByCustomerId(customerId);
        if (optionalWishlistEntity.isPresent()) {
            WishlistEntity wishlistEntity = optionalWishlistEntity.get();
            wishlistEntity.getProducts().removeIf(item -> item.getId().equals(productId));
            return mapper.toWishlist(repository.save(wishlistEntity));
        } else {
            var message = "Not found wishlist for customer '" + customerId + "'.";
            throw new WishlistNotFoundException(message);
        }
    }

    @Override
    public Wishlist addProduct(String customerId, Product product) {
        try {
            Optional<WishlistEntity> optionalWishlistEntity = repository.findByCustomerId(customerId);
            if (optionalWishlistEntity.isPresent()) {
                WishlistEntity wishlistEntity = optionalWishlistEntity.get();
                boolean alreadyAdded = wishlistEntity.getProducts()
                        .stream()
                        .anyMatch(existentProduct -> existentProduct.getId().equals(product.getId()));
                if (alreadyAdded) {
                    log.warn("Product '{}' already added in wishlist from customer '{}'", product.getId(), customerId);
                    return mapper.toWishlist(optionalWishlistEntity.get());
                } else {
                    wishlistEntity.getProducts().add(mapper.toProductEntity(product));
                    WishlistEntity saved = repository.save(wishlistEntity);
                    log.info("Wishlist '{}' from customer '{}' was updated", saved.getId(), saved.getCustomerId());
                    return mapper.toWishlist(saved);
                }
            }
        } catch (Exception e) {
            String message = "Something was wrong while adding product "
                    + product.getId() + " for customer '" + customerId + "'.";
            log.error("ERROR: {}", message);
            throw new WishlistUpdateException(message, e);
        }
        return null;
    }

    @Override
    public Set<Product> findAllProductsByCustomer(String customerId) {
        return repository.findByCustomerId(customerId)
                .map(entity -> entity.getProducts()
                        .stream()
                        .map(mapper::toProduct)
                        .collect(Collectors.toSet())
                )
                .orElseGet(Set::of);
    }

    @Override
    public Optional<Wishlist> findWishlistByCustomer(String customerId) {
        return repository.findByCustomerId(customerId).map(mapper::toWishlist);
    }
}
