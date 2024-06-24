package com.azusah.wishlist.usecase.impl;

import com.azusah.wishlist.domain.entity.Product;
import com.azusah.wishlist.domain.entity.User;
import com.azusah.wishlist.domain.entity.Wishlist;
import com.azusah.wishlist.usecase.AddProductToWishlistUseCase;
import org.springframework.stereotype.Component;

@Component
public class AddProductToWishlistUseCaseImpl implements AddProductToWishlistUseCase {

    @Override
    public Wishlist execute(User user, Product product) {
        if (user != null && product != null && user.isLogged()) {
            return new Wishlist();
        }
        return null;
    }
}
