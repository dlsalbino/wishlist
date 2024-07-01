package com.azusah.wishlist.domain.entity;

import java.util.HashSet;
import java.util.Set;

public class Wishlist {
    private String clientId;
    private Set<Product> products = new HashSet<>();

    public Wishlist(String clientId, Product product) {
        this.clientId = clientId;
        this.products.add(product);
    }

    public Wishlist(String clientId, Set<Product> products) {
        this.clientId = clientId;
        this.products.addAll(products);
    }

    private Wishlist(Builder builder) {
        this.clientId = builder.clientId;
        this.products = builder.products;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientId() {
        return clientId;
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
        private String clientId;
        private Set<Product> products = new HashSet<>();

        private Builder() {
        }

        public Builder clientId(String clientId) {
            this.clientId = clientId;
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
