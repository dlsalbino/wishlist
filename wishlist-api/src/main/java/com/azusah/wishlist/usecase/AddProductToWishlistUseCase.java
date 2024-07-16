package com.azusah.wishlist.usecase;

import com.azusah.wishlist.core.domain.entity.Product;
import com.azusah.wishlist.core.domain.entity.Wishlist;

public interface AddProductToWishlistUseCase {

    Wishlist execute(String clientId, Product product);

}
