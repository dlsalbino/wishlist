package com.azusah.wishlist.core.domain.exception;

public class WishlistUpdateException extends RuntimeException {

    public WishlistUpdateException(String message, Throwable cause){
        super(message);
    }
}
