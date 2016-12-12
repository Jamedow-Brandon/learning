package com.jamedow.learning.dmo;


import com.sun.istack.internal.NotNull;

/**
 * Created by 365 on 2016/12/12 0012.
 */
public class User {

    @NotNull
    private int id;

    private String username;

    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
