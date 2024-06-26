package com.azusah.wishlist.usecase.impl;

import com.azusah.wishlist.domain.entity.Product;
import com.azusah.wishlist.domain.entity.Wishlist;
import com.azusah.wishlist.domain.exception.WishlistLimitAchievedException;
import com.azusah.wishlist.gateway.RetrieveWishlistGateway;
import com.azusah.wishlist.gateway.SaveWishlistGateway;
import com.azusah.wishlist.usecase.AddProductToWishlistUseCase;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Objects.nonNull;

@Component
public class AddProductToWishlistUseCaseImpl implements AddProductToWishlistUseCase {

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
                    wishlist.getProducts().add(product);
                    return wishlist;
                } else {
                    throw new WishlistLimitAchievedException("The Wishlist has achieved the limit of " + WISHLIST_MAX_LIMIT + " products.");
                }
            } else {
                return saveWishListGateway.save(userId, product);
            }
        }
        return null;
    }
}
