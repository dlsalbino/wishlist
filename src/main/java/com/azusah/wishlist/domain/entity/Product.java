package com.azusah.wishlist.domain.entity;

public class Product {

    private String id;
    private String name;
    private String image;
    private String value;
    private String link;

    public Product() {
    }

    public Product(String id, String name, String image, String value, String link) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.value = value;
        this.link = link;
    }

    public Product(String name, String image, String value, String link) {
        this.name = name;
        this.image = image;
        this.value = value;
        this.link = link;
    }

    public static Product builder() {
        return new Product();
    }

    public Product id(String id) {
        this.id = id;
        return this;
    }

    public Product name(String name) {
        this.name = name;
        return this;
    }

    public Product image(String image) {
        this.image = image;
        return this;
    }

    public Product value(String value) {
        this.value = value;
        return this;
    }

    public Product link(String link) {
        this.link = link;
        return this;
    }

    public Product build() {
        return this;
    }
}
