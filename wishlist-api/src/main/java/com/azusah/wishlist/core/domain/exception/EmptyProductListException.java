package com.azusah.wishlist.core.domain.exception;

public class EmptyProductListException extends RuntimeException {
    public EmptyProductListException(String message) {
        super(message);
    }
}
