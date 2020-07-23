package com.drops.domain;


public class UserInfo
{
    private long id;
    private String username;

    public UserInfo(long id, String username, long role) {
        this.id = id;
        this.username = username;
        this.role = role;
    } private long role;
    public UserInfo() {}
    public long getId() { return this.id; }



    public void setId(long id) { this.id = id; }



    public String getUsername() { return this.username; }



    public void setUsername(String username) { this.username = username; }



    public long getRole() { return this.role; }



    public void setRole(long role) { this.role = role; }
}
