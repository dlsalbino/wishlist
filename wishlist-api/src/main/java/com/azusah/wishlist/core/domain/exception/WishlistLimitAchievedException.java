package com.azusah.wishlist.core.domain.exception;

public class WishlistLimitAchievedException extends RuntimeException {

    public WishlistLimitAchievedException(String message) {
        super(message);
    }
}
