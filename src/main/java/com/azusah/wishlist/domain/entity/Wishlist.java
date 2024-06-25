package com.azusah.wishlist.domain.entity;

import java.util.HashSet;
import java.util.Set;

public class Wishlist {
    private String id;
    private User user;
    private Set<Product> products = new HashSet<>();

    public Wishlist() {
    }

    public Wishlist(User user, Set<Product> products) {
        this.user = user;
        this.products = products;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
