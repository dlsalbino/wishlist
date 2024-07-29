package com.azusah.wishlist.core.domain.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {

    private final String id;
    private final String name;
    private final String image;
    private final BigDecimal value;
    private final String link;

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

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getLink() {
        return link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id)
                && Objects.equals(name, product.name)
                && Objects.equals(image, product.image)
                && Objects.equals(value, product.value)
                && Objects.equals(link, product.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, image, value, link);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", value='" + value + '\'' +
                ", link='" + link + '\'' +
                '}';
    }

    public static class Builder {
        private String id;
        private String name;
        private String image;
        private BigDecimal value;
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

        public Builder value(BigDecimal value) {
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
