package com.azusah.wishlist.usecase.impl;

import com.azusah.wishlist.domain.entity.Product;
import com.azusah.wishlist.gateway.RetrieveProductsGateway;
import com.azusah.wishlist.usecase.ListAllProductsUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ListAllProductsUseCaseImpl implements ListAllProductsUseCase {

    private static final Logger log = LoggerFactory.getLogger(ListAllProductsUseCaseImpl.class);
    private final RetrieveProductsGateway retrieveProductsGateway;

    @Autowired
    public ListAllProductsUseCaseImpl(RetrieveProductsGateway retrieveProductsGateway){
        this.retrieveProductsGateway = retrieveProductsGateway;
    }

    @Override
    public Set<Product> execute(String userId) {
        Set<Product> allByUser = retrieveProductsGateway.findAllByUser(userId);
        log.info("Retrieved {} products from client '{}'", allByUser.size(), userId);
        return allByUser;
    }
}
