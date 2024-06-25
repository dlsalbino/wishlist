package com.azusah.wishlist.domain.entity;

public class User {

    private final String id;
    private final boolean logged;

    public User(Builder builder) {
        this.id = builder.id;
        this.logged = builder.logged;
    }

    public String getId() {
        return this.id;
    }

    public boolean isLogged() {
        return this.logged;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private String id;
        private boolean logged;

        private Builder(){
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder logged(boolean logged) {
            this.logged = logged;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
