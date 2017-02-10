package com.green0.customerApp.model;

/**
 * Created by ironman on 10/11/16.
 */

import com.google.gson.annotations.SerializedName;


public class UserLogin {
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;

    public UserLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
