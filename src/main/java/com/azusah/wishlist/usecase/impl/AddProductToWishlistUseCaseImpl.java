package com.azusah.wishlist.usecase.impl;

import com.azusah.wishlist.domain.entity.Product;
import com.azusah.wishlist.domain.entity.Wishlist;
import com.azusah.wishlist.domain.exception.WishlistLimitAchievedException;
import com.azusah.wishlist.gateway.RetrieveWishlistGateway;
import com.azusah.wishlist.gateway.SaveWishlistGateway;
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
    private final RetrieveWishlistGateway retrieveWishListGateway;
    private final SaveWishlistGateway saveWishListGateway;

    public AddProductToWishlistUseCaseImpl(RetrieveWishlistGateway retrieveWishListGateway,
                                           SaveWishlistGateway saveWishListGateway) {
        this.retrieveWishListGateway = retrieveWishListGateway;
        this.saveWishListGateway = saveWishListGateway;
    }

    @Override
    public Wishlist execute(String userId, Product product) {
        if (nonNull(userId) && nonNull(product)) {
            Optional<Wishlist> retrievedWishlist = retrieveWishListGateway.findWishlistByUser(userId);
            if (retrievedWishlist.isPresent()) {
                var wishlist = retrievedWishlist.get();
                if (wishlist.getProducts().size() < WISHLIST_MAX_LIMIT) {
                    return saveWishListGateway.addProduct(userId, product);
                } else {
                    var message = "The Wishlist has achieved the limit of " + WISHLIST_MAX_LIMIT + " products.";
                    log.warn("END: {}", message);
                    throw new WishlistLimitAchievedException(message);
                }
            } else {
                return saveWishListGateway.save(userId, product);
            }
        }
        return null;
    }
}
