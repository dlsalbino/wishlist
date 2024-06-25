package com.azusah.wishlist.usecase.impl;

import com.azusah.wishlist.domain.entity.Product;
import com.azusah.wishlist.domain.entity.User;
import com.azusah.wishlist.domain.entity.Wishlist;
import com.azusah.wishlist.gateway.RetrieveWishListGateway;
import com.azusah.wishlist.gateway.SaveWishListGateway;
import com.azusah.wishlist.usecase.AddProductToWishlistUseCase;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;

@Component
public class AddProductToWishlistUseCaseImpl implements AddProductToWishlistUseCase {

    private final static Integer WISHLIST_MAX_LIMIT = 20;
    private final RetrieveWishListGateway retrieveWishListGateway;
    private final SaveWishListGateway saveWishListGateway;

    public AddProductToWishlistUseCaseImpl(RetrieveWishListGateway retrieveWishListGateway,
                                           SaveWishListGateway saveWishListGateway) {
        this.retrieveWishListGateway = retrieveWishListGateway;
        this.saveWishListGateway = saveWishListGateway;
    }

    @Override
    public Wishlist execute(User user, Product product) {
        if (user != null && user.isLogged() && product != null) {
            Optional<Wishlist> retrievedWishlist = retrieveWishListGateway.findWishlistByUser(user.getId());
            if (retrievedWishlist.isPresent()) {
                var wishlist = retrievedWishlist.get();
                if (wishlist.getProducts().size() < WISHLIST_MAX_LIMIT) {
                    wishlist.getProducts().add(product);
                    return wishlist;
                } else {
                    throw new RuntimeException("A lista atingiu o limite de " + WISHLIST_MAX_LIMIT + " produtos.");
                }
            } else {
                var products = new HashSet<Product>();
                products.add(product);
                Wishlist wishlist = new Wishlist(user, products);
                return saveWishListGateway.save(wishlist);
            }
        }
        return null;
    }
}
