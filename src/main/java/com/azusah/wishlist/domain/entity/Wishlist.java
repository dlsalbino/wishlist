package com.azusah.wishlist.domain.entity;

import java.util.HashSet;
import java.util.Set;

public class Wishlist {
    private String userId;
    private Set<Product> products = new HashSet<>();

    public Wishlist(String userId, Product product) {
        this.userId = userId;
        this.products.add(product);
    }

    public Wishlist(String userId, Set<Product> products) {
        this.userId = userId;
        this.products.addAll(products);
    }

    private Wishlist(Builder builder) {
        this.userId = builder.userId;
        this.products = builder.products;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void addProducts(Set<Product> products) {
        this.products.addAll(products);
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public static class Builder {
        private String userId;
        private Set<Product> products = new HashSet<>();

        private Builder() {
        }

        public Builder userId(String userId) {
            this.userId = userId;
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
