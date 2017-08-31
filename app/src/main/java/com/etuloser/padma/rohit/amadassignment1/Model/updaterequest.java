package com.etuloser.padma.rohit.amadassignment1.Model;

/**
 * Created by Rohit on 8/31/2017.
 */

public class updaterequest
{

    public user user;
    public String token;

    public com.etuloser.padma.rohit.amadassignment1.Model.user getUser() {
        return user;
    }

    public void setUser(com.etuloser.padma.rohit.amadassignment1.Model.user user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
