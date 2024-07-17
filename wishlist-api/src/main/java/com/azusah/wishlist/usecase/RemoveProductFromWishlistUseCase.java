package com.azusah.wishlist.usecase;

import com.azusah.wishlist.core.domain.entity.Wishlist;

public interface RemoveProductFromWishlistUseCase {

    Wishlist execute(String customerId, String productId);
}
