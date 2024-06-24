package com.azusah.wishlist.domain.entity;

public class User {

    private final String id;
    private final boolean logged;


    private User(String id, boolean logged){
        this.id = id;
        this.logged = logged;
    }
    public String getId() {
        return id;
    }

    public boolean isLogged() {
        return logged;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private String id;
        private boolean logged;

        public UserBuilder id(String id) {
            this.id = id;
            return this;
        }

        public UserBuilder logged(boolean logged) {
            this.logged = logged;
            return this;
        }

        public User build() {
            return new User(id, logged);
        }
    }
}
