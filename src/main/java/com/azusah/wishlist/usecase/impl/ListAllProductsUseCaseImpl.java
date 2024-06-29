package com.azusah.wishlist.usecase.impl;

import com.azusah.wishlist.domain.entity.Product;
import com.azusah.wishlist.domain.exception.EmptyProductListException;
import com.azusah.wishlist.gateway.PersistenceGateway;
import com.azusah.wishlist.usecase.ListAllProductsUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ListAllProductsUseCaseImpl implements ListAllProductsUseCase {

    private static final Logger log = LoggerFactory.getLogger(ListAllProductsUseCaseImpl.class);
    private final PersistenceGateway persistenceGateway;

    @Autowired
    public ListAllProductsUseCaseImpl(PersistenceGateway persistenceGateway) {
        this.persistenceGateway = persistenceGateway;
    }

    @Override
    public Set<Product> execute(String userId) {
        Set<Product> products = persistenceGateway.findAllProductsByUser(userId);
        if (products.isEmpty())
            throw new EmptyProductListException("There is no products for client '" + userId + "'.");

        log.info("Retrieved {} products from client '{}'", products.size(), userId);
        return products;
    }
}
