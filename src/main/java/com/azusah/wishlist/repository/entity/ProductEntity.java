package com.azusah.wishlist.repository.entity;

public class ProductEntity {

    private String id;
    private String name;
    private String image;
    private String value;
    private String link;

    public ProductEntity() {
    }

    public ProductEntity(String id, String name, String image, String value, String link) {
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

    public String getValue() {
        return value;
    }

    public String getLink() {
        return link;
    }
}
