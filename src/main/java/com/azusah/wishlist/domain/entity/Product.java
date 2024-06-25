package com.azusah.wishlist.domain.entity;

public class Product {

    private final String id;
    private final String name;
    private final String image;
    private final String value;
    private final String link;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getValue() {
        return value;
    }

    public String getLink() {
        return link;
    }

    public Product(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.image = builder.image;
        this.value = builder.value;
        this.link = builder.link;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String name;
        private String image;
        private String value;
        private String link;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder image(String image) {
            this.image = image;
            return this;
        }

        public Builder value(String value) {
            this.value = value;
            return this;
        }

        public Builder link(String link) {
            this.link = link;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
