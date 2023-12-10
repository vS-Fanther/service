package com.test.service.user.model;

public class User {
    private String id;
    private String name;
    private String username;
    private String surname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private User user;

        private Builder() {
            this.user = new User();
        }

        public Builder id(String id) {
            user.id = id;
            return this;
        }

        public Builder name(String name) {
            user.name = name;
            return this;
        }

        public Builder username(String username) {
            user.username = username;
            return this;
        }

        public Builder surname(String surname) {
            user.surname = surname;
            return this;
        }

        public User build() {
            return user;
        }
    }
}
