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

    private Wishlist(Builder builder) {
        this.user = builder.user;
        this.id = builder.id;
        this.products = builder.products;
    }

    public String getId() {
        return id;
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private User user;
        private Set<Product> products = new HashSet<>();

        private Builder() {
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder products(Set<Product> products) {
            this.products = products;
            return this;
        }

        public Wishlist build() {
            return new Wishlist(this);
        }

    }
}
