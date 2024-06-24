package com.azusah.wishlist.domain.entity;

public class User {

    private String id;
    private boolean logged;

    public User(){
    }

    public String getId() {
        return id;
    }

    public boolean isLogged() {
        return logged;
    }

    public static User builder(){
        return new User();
    }

    public User id(String id) {
        this.id = id;
        return this;
    }

    public User logged(boolean logged) {
        this.logged = logged;
        return this;
    }

    public User build(){
        return this;
    }
}
