package com.etuloser.padma.rohit.amadassignment1.Interface;

import com.etuloser.padma.rohit.amadassignment1.Model.loginrequest;
import com.etuloser.padma.rohit.amadassignment1.Model.loginresponse;
import com.etuloser.padma.rohit.amadassignment1.Model.updaterequest;
import com.etuloser.padma.rohit.amadassignment1.Model.user;
import com.etuloser.padma.rohit.amadassignment1.Model.viewuser;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Rohit on 8/31/2017.
 */

public interface IRetrofit {

    @POST("api/login")
    Call<loginresponse> login(@Body loginrequest lr);


    @POST("api/register")
    Call<loginresponse> register(@Body user lr);


    @POST("api/view")
    Call<viewuser> viewuser(@Body loginresponse lr);


    @POST("api/update")
    Call<loginresponse> updateuser(@Body updaterequest lr);


}
