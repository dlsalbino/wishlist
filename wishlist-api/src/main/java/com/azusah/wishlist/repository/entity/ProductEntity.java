package com.azusah.wishlist.repository.entity;

import java.math.BigDecimal;

public class ProductEntity {

    private String id;
    private String name;
    private String image;
    private BigDecimal value;
    private String link;

    public ProductEntity() {
    }

    public ProductEntity(String id, String name, String image, BigDecimal value, String link) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.value = value;
        this.link = link;
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
        if (!(o instanceof ProductEntity product)) return false;

        if (!getId().equals(product.getId())) return false;
        if (!getName().equals(product.getName())) return false;
        if (!getImage().equals(product.getImage())) return false;
        if (!getValue().equals(product.getValue())) return false;
        if (!getLink().equals(product.getLink())) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = 31 * getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getImage().hashCode();
        result = 31 * result + getValue().hashCode();
        result = 31 * result + getLink().hashCode();
        return result;
    }
}
