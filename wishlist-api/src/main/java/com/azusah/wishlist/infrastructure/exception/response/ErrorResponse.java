package com.azusah.wishlist.infrastructure.exception.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class ErrorResponse {

    @Schema(example = "Full Wishlist", description = "The error's title.")
    private String message;
    @Schema(example = "The Wishlist has achieved the limit of 20 products.", description = "The reason of the error.")
    private List<String> details;

    public ErrorResponse() {
    }

    public ErrorResponse(String message, List<String> details) {
        this.message = message;
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getDetails() {
        return details;
    }
}