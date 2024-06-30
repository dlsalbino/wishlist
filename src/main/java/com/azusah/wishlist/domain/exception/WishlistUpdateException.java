package com.azusah.wishlist.domain.exception;

public class WishlistUpdateException extends RuntimeException {

    public WishlistUpdateException(String message, Throwable cause){
        super(message);
    }
}
