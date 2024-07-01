package com.azusah.wishlist.usecase;

import com.azusah.wishlist.domain.entity.Product;
import com.azusah.wishlist.domain.entity.Wishlist;

public interface AddProductToWishlistUseCase {

    Wishlist execute(String clientId, Product product);

}
