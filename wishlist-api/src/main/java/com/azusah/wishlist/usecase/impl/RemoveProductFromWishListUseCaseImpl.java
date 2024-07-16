package com.azusah.wishlist.usecase.impl;

import com.azusah.wishlist.core.domain.entity.Product;
import com.azusah.wishlist.core.domain.entity.Wishlist;
import com.azusah.wishlist.gateway.PersistenceGateway;
import com.azusah.wishlist.usecase.RemoveProductFromWishlistUseCase;
import org.springframework.stereotype.Component;

@Component
public class RemoveProductFromWishListUseCaseImpl implements RemoveProductFromWishlistUseCase {

    private final PersistenceGateway persistenceGateway;

    public RemoveProductFromWishListUseCaseImpl(PersistenceGateway persistenceGateway) {
        this.persistenceGateway = persistenceGateway;
    }

    @Override
    public Wishlist execute(Product product, String customerId) {
        return persistenceGateway.removeProduct(customerId, product);
    }
}
