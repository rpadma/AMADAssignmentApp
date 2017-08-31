package com.etuloser.padma.rohit.amadassignment1.Model;

/**
 * Created by Rohit on 8/31/2017.
 */

public class loginresponse {

    public String code;
    public String token;
    public String success;
    public String message;
    public String email;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
