package com.drops.domain;

import com.drops.domain.User;

public class User {
    private long id;
    private String username;

    public User(long id, String username, String password, long role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    private String password;
    private long role;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getUsername() {
        return this.username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return this.password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public long getRole() {
        return this.role;
    }


    public void setRole(long role) {
        this.role = role;
    }
}