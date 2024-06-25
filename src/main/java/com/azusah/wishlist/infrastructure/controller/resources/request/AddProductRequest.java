package com.azusah.wishlist.infrastructure.controller.resources.request;

import java.util.HashSet;
import java.util.Set;

public class AddProductRequest {
    private UserRequest user;
    private Set<ProductRequest> products = new HashSet<>();

    public AddProductRequest() {
    }

    public AddProductRequest(UserRequest user, Set<ProductRequest> products) {
        this.user = user;
        this.products = products;
    }

    public UserRequest getUser() {
        return user;
    }

    public Set<ProductRequest> getProducts() {
        return products;
    }
}
