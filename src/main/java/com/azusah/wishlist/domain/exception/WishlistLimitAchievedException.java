package com.azusah.wishlist.domain.exception;

public class WishlistLimitAchievedException extends RuntimeException {

    public WishlistLimitAchievedException(String message) {
        super(message);
    }
}
