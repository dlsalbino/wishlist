package com.azusah.wishlist.domain.exception;

public class EmptyProductListException extends RuntimeException {
    public EmptyProductListException(String message) {
        super(message);
    }
}
