package com.azusah.wishlist.usecase.impl;

import com.azusah.wishlist.core.domain.entity.Product;
import com.azusah.wishlist.core.domain.entity.Wishlist;
import com.azusah.wishlist.core.domain.exception.WishlistLimitAchievedException;
import com.azusah.wishlist.gateway.PersistenceGateway;
import com.azusah.wishlist.usecase.AddProductToWishlistUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Objects.nonNull;

@Component
public class AddProductToWishlistUseCaseImpl implements AddProductToWishlistUseCase {

    private static final Logger log = LoggerFactory.getLogger(AddProductToWishlistUseCaseImpl.class);
    private final static Integer WISHLIST_MAX_LIMIT = 20;
    private final PersistenceGateway persistenceGateway;

    public AddProductToWishlistUseCaseImpl(PersistenceGateway persistenceGateway) {
        this.persistenceGateway = persistenceGateway;
    }

    @Override
    public Wishlist execute(String clientId, Product product) {
        if (nonNull(clientId) && nonNull(product)) {
            Optional<Wishlist> retrievedWishlist = persistenceGateway.findWishlistByClient(clientId);
            if (retrievedWishlist.isPresent()) {
                var wishlist = retrievedWishlist.get();
                if (wishlist.getProducts().size() < WISHLIST_MAX_LIMIT) {
                    return persistenceGateway.addProduct(clientId, product);
                } else {
                    var message = "The Wishlist has achieved the limit of " + WISHLIST_MAX_LIMIT + " products.";
                    log.warn("END: {}", message);
                    throw new WishlistLimitAchievedException(message);
                }
            } else {
                return persistenceGateway.save(clientId, product);
            }
        }
        return null;
    }
}
